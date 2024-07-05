package com.sh.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sh.model.config.ThreadPoolConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class BackendWorker {
    
    private final ThreadPoolConfig threadPoolConfig;
    private ThreadPoolExecutor threadPoolExecutor;
    
    @PostConstruct
    private void initThreadPool() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("shs-%d").build();
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(threadPoolConfig.getQueueSize());
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        threadPoolExecutor = new ThreadPoolExecutor(
                threadPoolConfig.getCorePoolSize(),
                threadPoolConfig.getMaximumPoolSize(),
                threadPoolConfig.getKeepAliveTime(),
                TimeUnit.MINUTES,
                queue,
                factory,
                handler
        );
        log.info(">>>>>>>>>>>>>>>>>线程池初始化成功>>>>>>>>>>>>>>>>>{}", threadPoolExecutor);
    }
    
    public void shutdown() {
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdownNow();
        }
    }
    
    public void submit(final Runnable task) {
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