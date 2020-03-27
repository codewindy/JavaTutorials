package com.codewindy.mongodb.service.impl;

import com.codewindy.mongodb.pojo.ApiResponseJson;
import com.codewindy.mongodb.service.MikrotikService;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ApiResponseJson login(String username, String password) {
        ApiConnection con = null;
        // connect to router
        try {
            con = ApiConnection.connect("192.168.2.2");
            con.setTimeout(5000);
            // set command timeout to 5 seconds
            con.login(username,password);
            // log in to router
            List<Map<String, String>> execute = con.execute("/ip/address/print");
            // execute a command
            con.close();
            // disconnect from router
            return new ApiResponseJson(execute);

        } catch (MikrotikApiException e) {
            log.info("登录RouterOS失败 = {}",e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

    @Override
    public ApiResponseJson createPPPOEServer(String ipPoolRange) {
       /*/ip ipsec proposal
        set [ find default=yes ] enc-algorithms=aes-128-cbc
                /ip pool
        add name=pool1 ranges=10.0.0.1-10.0.4.1
                /ip address
        add address=192.168.2.2/24 interface=wan network=192.168.2.0
                /ip cloud
        set update-time=no
                /ip firewall nat
        add action=masquerade chain=srcnat
                /ip ipsec policy
        set 0 dst-address=0.0.0.0/0 src-address=0.0.0.0/0
                /ip route
        add distance=1 gateway=192.168.2.1
                /ip service
        set www port=100
        set winbox port=4569
        */

        return null;
    }

    @Override
    public ApiResponseJson getPcapFileDetail() {
        ApiConnection con = null;
        // connect to router
        try {
            con = ApiConnection.connect("192.168.2.2");
            con.setTimeout(5000);
            // set command timeout to 5 seconds
            con.login("admin","");
            // log in to router
            List<Map<String, String>> resultMapList = con.execute("/file/print");
            List<String> content = resultMapList.stream().filter(s-> s.get("type").contains(".cap")).map(s -> s.get("contents")).collect(Collectors.toList());
            // execute a command
            con.close();
            // disconnect from router
            return new ApiResponseJson(content);
        } catch (MikrotikApiException e) {
            log.info("获取PPPOESession详情失败 = {}",e.getMessage());
            return new ApiResponseJson(e.getMessage());
        }
    }

    public ApiResponseJson downloadPPPOESession(String pcapFileName) {
        //下载Packet Sniffer下载创建的pcap文件
        return null;
    }

}