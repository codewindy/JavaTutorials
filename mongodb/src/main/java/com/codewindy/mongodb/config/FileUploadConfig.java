
package com.codewindy.mongodb.config;
/**
 * @author jkwindy@126.com
 * @date 2019-05-24 10:55
 */

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {
    /**
     * 设置可以上传文件大小 默认的tomcat -1 unlimited
     * @return
     */
    @Bean
   public  MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(1024L));
        factory.setMaxRequestSize(DataSize.ofMegabytes(1024L));
        return factory.createMultipartConfig();
    }
}

