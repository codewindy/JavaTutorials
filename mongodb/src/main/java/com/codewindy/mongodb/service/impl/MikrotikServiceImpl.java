package com.codewindy.mongodb.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
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
            //开始执行抓包pppoe-session
            String command4capFileName = "/tool/sniffer/set file-name=%s.cap filter-mac-protocol=pppoe memory-limit=1000KiB";
            con.execute(String.format(command4capFileName, DateUtil.today()));
            log.info("开始执行抓包pppoe-session==={}", String.format(command4capFileName, DateUtil.today()));
            List<Map<String, String>> execute = con.execute("/tool/sniffer/start mac-protocol=pppoe interface=ether1 direction=rx");
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

    @Override
    public ApiResponseJson getPcapFileDetail() {
        ApiConnection con = null;
        // connect to router
        try {
            con = initApiConnection("admin", "");
            // log in to router
            List<Map<String, String>> resultMapList = con.execute("/file/print");
            //获取每个cap数据包里面的content
            List<String> contentList = resultMapList.stream().filter(s -> s.get("type").contains(".cap") || s.get("type").contains(".pcap")).map(s -> s.get("contents")).collect(Collectors.toList());
            //获取每个content里面的pppoe 账号密码
            Set<String> pppoeAccountSet = contentList.stream().map(content -> StrUtil.subBetween(content, "user", "authentication").trim()).collect(Collectors.toSet());
            List<PppoeDetail> pppoeDetailList = Lists.newArrayListWithCapacity(pppoeAccountSet.size());
            for (String pppoeAccount : pppoeAccountSet) {
                List<String> passwordlist = contentList.stream().map(content -> StrUtil.subBetween(content, pppoeAccount, "L").trim()).distinct().collect(Collectors.toList());
                PppoeDetail pppoeDetail = new PppoeDetail();
                pppoeDetail.setAccount(pppoeAccount);
                if (!CollectionUtils.isEmpty(passwordlist)) {
                    pppoeDetail.setPassword(passwordlist.get(0));
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

    public ApiResponseJson downloadPPPOESession(String pcapFileName) {
        //下载Packet Sniffer下载创建的pcap文件
        //TODO
        //1. 不能直接抓去pppoe-session的数据
        //2. pppoe 服务器的ip 地址动态传入
        //3. 切换网络会出现winbox退出
        //4. file print detail 不能查看2kb之外大文件，无法解析出contents内容
        //5. 登录接口只调用一次，使用redis缓存账号密码
        //6. 统一修改接口返回参数 T data
        return null;
    }

}