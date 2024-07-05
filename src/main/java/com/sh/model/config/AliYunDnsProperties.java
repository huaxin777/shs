package com.sh.model.config;

import com.sh.model.dto.DnsConfigDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 27300
 */

@Data
@Configuration
@ConfigurationProperties(
        prefix = "aliyun.dns"
)
public class AliYunDnsProperties {
    private Boolean enable;
    List<DnsConfigDto> dnsConfig;
}
