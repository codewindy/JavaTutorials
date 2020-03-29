package com.codewindy.mongodb.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codewindy.mongodb.pojo.ApiResponseJson;
import com.codewindy.mongodb.pojo.PppoeDetail;
import com.codewindy.mongodb.service.MikrotikService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author codewindy
 * @date 2020-03-25 9:44 PM
 * @since 1.0.0
 */
@Service
@Slf4j
public class MikrotikServiceImpl implements MikrotikService {
    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public ApiResponseJson login(String username, String password) {
        ApiConnection con = null;
        // connect to router
        try {
            con = initApiConnection(username, password);
            // log in to router
            List<Map<String, String>> execute = con.execute("/ip/address/print");
            // execute a command
            con.close();
            // disconnect from router
            return new ApiResponseJson(execute);

        } catch (MikrotikApiException e) {
            log.info("登录RouterOS失败 = {}", e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

    /*
    /ip pool add name=pppoe_pool1 ranges=10.10.10.2-10.10.10.254
    /ppp profile add name=pppoe_profile local-address=10.10.10.1 remote-address=pppoe_pool1
    /interface pppoe-server server add authentication=pap service-name=PPPoE_Server interface=wan one-session-per-host=yes default-profile=pppoe_profile
    Mikrotik API 调用和直接winbox的不同，需要添加/ 转义
       */
    @Override
    public ApiResponseJson createPPPOEServer(String ipPoolRange) {
        ApiConnection con = null;
        // connect to router
        try {
            con = initApiConnection("admin", "");
            // log in to router
            con.execute("/ip/pool/print");
            con.execute("/ip/pool/add name=pool1 ranges=10.10.10.2-10.10.20.1");
            con.execute("/ppp/profile/add name=pppoe_10M remote-address=pool1");
            con.execute("/ppp/secret/add name=0327 password=0327Test service=pppoe profile=pppoe_10M");
            con.execute("/interface/pppoe-server/server/add authentication=pap service-name=PPPoE_Server interface=ether1 one-session-per-host=yes default-profile=pppoe_10M");
            con.execute("/interface/pppoe-server/server/enable numbers=0");
            //开始执行抓包pppoe-session file-limit=10KiB  控制文件大小 内存好像没起作用
            String command4capFileName = "/tool/sniffer/set file-name=%s.cap filter-mac-protocol=pppoe file-limit=10KiB memory-limit=10KiB";
            con.execute(String.format(command4capFileName, DateUtil.today()));
            log.info("开始执行抓包pppoe-session==={}", String.format(command4capFileName, DateUtil.today()));
            con.execute("/tool/sniffer/start mac-protocol=pppoe interface=ether1 direction=rx");
            List<Map<String, String>> resultMapList = con.execute("/file/print");
            for (Map<String, String> fileMap : resultMapList) {
                if ((fileMap.get("type").contains(".cap") || fileMap.get("type").contains(".pcap"))&& Integer.parseInt(fileMap.get("size"))>1000){
                    //创建pppoe服务器抓包时候控制文件size 一般大于2000B 就无法执行file print detail，即不能在terminal上解析报文了
                    con.execute("/tool/sniffer/stop");
                }
            }
            return new ApiResponseJson("初始化pppoe服务器成功!");
        } catch (MikrotikApiException e) {
            log.info("登录RouterOS失败 = {}", e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

    private ApiConnection initApiConnection(String username, String password) throws MikrotikApiException {
        ApiConnection con;
        con = ApiConnection.connect("192.168.2.2");
        con.setTimeout(5000);
        // set command timeout to 5 seconds
        con.login(username, password);
        return con;
    }

    /**
     * 解析后的json中包含了\u0000 空格
     *  {
     *         "account": "test4pppoe",
     *         "password": "123456Test\u0000\u0000\u0000\u0000\u0000\u0000"
     *     }
     * @return
     */
    @Override
    public ApiResponseJson getPcapFileDetail() {
        ApiConnection con = null;
        // connect to router
        try {
            con = initApiConnection("admin", "");
            // log in to router
            List<Map<String, String>> resultMapList = con.execute("/file/print");
            resultMapList.stream().filter(s -> s.get("type").contains(".cap") || s.get("type").contains(".pcap")).forEach(s -> System.out.println("pcap报文的size="+s.get("size")));
            //获取每个cap数据包里面的content
            List<String> contentList = resultMapList.stream().filter(s -> s.get("type").contains(".cap") || s.get("type").contains(".pcap")).map(s -> s.get("contents")).collect(Collectors.toList());
            //获取每个content里面的pppoe 账号密码
            Set<String> pppoeAccountSet = contentList.stream().filter(content -> content.contains("user")&& content.contains("authentication")).map(content -> StrUtil.subBetween(content, "user", "authentication").trim()).collect(Collectors.toSet());
            List<PppoeDetail> pppoeDetailList = Lists.newArrayListWithCapacity(pppoeAccountSet.size());
            for (String pppoeAccount : pppoeAccountSet) {
                List<String> passwordlist = contentList.stream().filter(content->content.contains(pppoeAccount)).map(content -> StrUtil.subBetween(content, pppoeAccount, "\u0000").trim()).distinct().collect(Collectors.toList());
                PppoeDetail pppoeDetail = new PppoeDetail();
                pppoeDetail.setAccount(pppoeAccount);
                if (!CollectionUtils.isEmpty(passwordlist)) {
                    //直接截取字符串 去除了二进制流中的乱码 \u0000空格 �LV
                    pppoeDetail.setPassword(StrUtil.subPre(passwordlist.get(0),16));
                }
                pppoeDetailList.add(pppoeDetail);
            }
            // execute a command
            con.close();
            // disconnect from router
            return new ApiResponseJson(pppoeDetailList);
        } catch (MikrotikApiException e) {
            log.info("获取PPPOESession详情失败 = {}", e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

    public ApiResponseJson downloadPPPOESession() {
        //下载Packet Sniffer下载创建的pcap文件
        //TODO
        //1. 不能直接抓去pppoe-session的数据
        //2. pppoe 服务器的ip 地址动态传入
        //3. 切换网络会出现winbox退出
        //4. file print detail 不能查看2kb之外大文件，无法解析出contents内容
        //5. 登录接口只调用一次，使用redis缓存账号密码
        //6. 统一修改接口返回参数 T data
        //7. 使用vim 或cat 来解析cap或pcap抓包文件
        ApiConnection con = null;
        // connect to router
        //C:\WBYF_IDEA
        try {
            con = initApiConnection("admin", "");
            // log in to router
            //List<Map<String, String>> resultMapList = con.execute("/file/print");
            Sftp sftp= JschUtil.createSftp("192.168.2.2", 22, "admin", "");
            //进入远程目录
            sftp.cd("/");
            System.out.println("sftp.pwd() = " + sftp.pwd());
            log.info("获取sftp文件当前路径",sftp.pwd());
            log.info("显示当前目录下的文件",sftp.ls("/"));
            List<String> fileList = sftp.ls("/");
            //下载远程文件
            fileList.stream().filter(s -> s.contains(".cap") || s.contains(".pcap")).forEach(capFile -> sftp.get(capFile, "C:\\WBYF_IDEA\\"));
            //上传本地文件
            //sftp.put("e:/test.jpg", "/opt/upload");
            //sftp.get("0325.cap", "C:\\WBYF_IDEA\\");
            //关闭连接
           // List<String> strings = FileUtil.readUtf8Lines("C:\\WBYF_IDEA\\0325.cap");

            sftp.close();
            return new ApiResponseJson("下载pcap数据文件成功");
        } catch (MikrotikApiException e) {
            log.info("下载pcap抓包文件失败 = {}", e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

}