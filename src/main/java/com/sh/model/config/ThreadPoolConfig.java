package com.sh.model.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AliYunMyConfig
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/4/1 10:57
 * @Author: SH
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolConfig {
	private Integer corePoolSize;
	private Integer maximumPoolSize;
	private Integer keepAliveTime;
	private Integer queueSize;
	
}
