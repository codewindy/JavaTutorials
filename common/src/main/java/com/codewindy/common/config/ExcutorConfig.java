package com.codewindy.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author codewindy
 * @date 2020-11-07 9:09 PM
 * @since 1.0.0
 * refer https://ld246.com/article/1600095988462
 */
@Configuration
public class ExcutorConfig {
    /** 核心线程数 */
    private int corePoolSize = 10;
    /** 最大线程数  */
    private int maxPoolSize = 50;
    /** 队列大小  */
    private int queueCapacity = 10;
    /** 线程最大空闲时间   */
    private int keepAliveSeconds = 150;

    @Bean("commonExecutor")
    public Executor commonExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("customExecutor-");
        executor.setKeepAliveSeconds(keepAliveSeconds);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}