package com.codewindy.mongodb.service;

import com.codewindy.mongodb.pojo.ApiResponseJson;

public interface MikrotikService {

    ApiResponseJson login(String username, String password);

    ApiResponseJson createPPPOEServer(String ipPoolRange);

    ApiResponseJson getPcapFileDetail();
}
