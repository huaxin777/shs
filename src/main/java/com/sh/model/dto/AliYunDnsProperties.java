package com.sh.model.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

/**
 * @author 27300
 */

@Data
@ConfigurationProperties(
        prefix = "aliyun.dns"
)
public class AliYunDnsProperties {
    private Boolean enable;
    List<DnsConfigDto> dnsConfig;
}
