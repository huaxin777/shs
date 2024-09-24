package com.sh.scheduled;

import com.sh.model.config.AliYunDnsProperties;
import com.sh.service.ManageIpv6Service;
import com.sh.service.impl.ManageIpv6ServiceImpl;
import com.sh.utils.BackendWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 27300
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DnsScheduled {
    
    private final AliYunDnsProperties aliYunDnsProperties;
    private final ManageIpv6Service manageIpv6Service;
    private final BackendWorker backendWorker;
    
    @Scheduled(fixedRate = 300000)
    public void updateDns(){
        if (aliYunDnsProperties.getEnable()) {
            backendWorker.submit(manageIpv6Service::updateDns);
        }
    }
}
