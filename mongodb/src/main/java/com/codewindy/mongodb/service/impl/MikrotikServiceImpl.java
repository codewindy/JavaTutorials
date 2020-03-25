package com.codewindy.mongodb.service.impl;

import com.codewindy.mongodb.pojo.ApiResponseJson;
import com.codewindy.mongodb.service.MikrotikService;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.stereotype.Service;

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
            con = ApiConnection.connect("192.168.88.1");
            con.setTimeout(5000);
            // set command timeout to 5 seconds
            con.login(username,password);
            // log in to router
            con.execute("/system resource print");
            // execute a command
            con.close();
            // disconnect from router
            return new ApiResponseJson();
        } catch (MikrotikApiException e) {
            e.printStackTrace();
            log.info("登录RouterOS失败 = {}",e.getMessage());
        }
        return new ApiResponseJson();
    }

    @Override
    public ApiResponseJson createPPPOEServer(String ipPoolRange) {
        /**
         * /ip pool
         * add name=pool1 ranges=10.16.0.2-10.16.0.254
         * /ip address
         * add address=192.168.199.22/24 comment=defconf interface=ether1 network=192.168.199.0
         * add address=192.168.199.122/24 interface=ether1 network=192.168.199.0
         * /ip cloud
         * set update-time=no
         * /ip dhcp-client
         * add dhcp-options=hostname,clientid disabled=no interface=ether1
         * /ip dns
         * set allow-remote-requests=yes servers=192.168.199.1
         * /ip firewall nat
         * add action=masquerade chain=srcnat out-interface-list=WAN
         * /ip route
         * add disabled=yes distance=1 gateway=192.168.199.1
         * [admin@fsm] > ppp export
         * # may/02/2019 15:45:52 by RouterOS 6.44.2
         * # software id = ZJ3M-ESHW
         *
         * /ppp profile
         * add dns-server=223.5.5.5 local-address=10.16.0.1 name=8M remote-address=pool1
         */
        return null;
    }

    @Override
    public ApiResponseJson downloadPPPOESession(String pcapFileName) {
        //下载Packet Sniffer下载创建的pcap文件
        return null;
    }

}