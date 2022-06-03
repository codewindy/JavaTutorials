package com.codewindy.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author codewindy
 * @date 2022-06-03 15:22
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan({"com.codewindy.h2","com.codewindy.common"})
//@EnableScheduling
@EnableWebMvc
@EnableSwagger2
    public class H2Application {

        public static void main(String[] args) {
            SpringApplication.run(H2Application.class, args);
        }

    }
