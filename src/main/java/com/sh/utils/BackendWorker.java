package com.sh.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 27300
 * @description: 用于后台异步处理请求
 **/
@Component
@Slf4j
public class BackendWorker {
    
    private static ThreadPoolExecutor threadPoolExecutor;
    @Value("${thread-pool.core-pool-size}")
    private Integer corePoolSize;
    @Value("${thread-pool.maximum-pool-size}")
    private Integer maximumPoolSize;
    @Value("${thread-pool.keep-alive-time}")
    private Integer keepAliveTime;
    @Value("${thread-pool.queue-size}")
    private Integer queueSize;
    public static Integer CORE_POOL_SIZE;
    public static Integer MAXIMUM_POOL_SIZE;
    public static Integer KEEP_ALIVE_TIME;
    public static Integer QUEUE_SIZE;
    
    @PostConstruct
    private void init(){
        CORE_POOL_SIZE = corePoolSize;
        MAXIMUM_POOL_SIZE = maximumPoolSize;
        KEEP_ALIVE_TIME = keepAliveTime;
        QUEUE_SIZE = queueSize;
        
    }
    
    @PostConstruct
    private static void initThreadPool() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("my_spring-%d").build();
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(QUEUE_SIZE);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                queue,
                factory,
                handler
        );
        log.info(">>>>>>>>>>>>>>>>>线程池初始化成功>>>>>>>>>>>>>>>>>{}", threadPoolExecutor);
    }
    
    public static void shutdown() {
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdownNow();
        }
    }
    
    public static void submit(final Runnable task) {
        if (task == null) {
            log.warn("empty task");
            return;
        }
        threadPoolExecutor.execute(() -> {
            try {
                task.run();
                log.info("线程池状态报告 - 运行状态：{}，池大小：{}，活跃线程数：{}，等待任务队列大小：{}，已完成任务数：{}",
                        threadPoolExecutor.isTerminated() ? "已终止" : "运行中",
                        threadPoolExecutor.getCorePoolSize(),
                        threadPoolExecutor.getActiveCount(),
                        threadPoolExecutor.getQueue().size(),
                        threadPoolExecutor.getCompletedTaskCount());
            } catch (Exception e) {
                log.error("task exception={}", e.getMessage());
            }
        });
    }
}