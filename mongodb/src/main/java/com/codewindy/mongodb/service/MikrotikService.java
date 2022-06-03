package com.codewindy.mongodb.service;

import com.codewindy.common.utils.ApiResult;

public interface MikrotikService {

    ApiResult login(String username, String password);

    ApiResult createPPPOEServer(String ipPoolRange);

    ApiResult getPcapFileDetail();

    ApiResult downloadPPPOESession();

    ApiResult parseLocalPcapFile();
}
