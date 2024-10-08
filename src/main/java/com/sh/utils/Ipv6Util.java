package com.sh.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * @author SH
 */
@Slf4j
public class Ipv6Util {
    
    private static String getLocalIpv6AddressByHostname() {
        List<String> ipv6List = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress address = inetAddresses.nextElement();
                    
                    // 检查是否为IPv6地址
                    if (address instanceof Inet6Address) {
                        String hostAddress = address.getHostAddress();
                        String ipv6 = hostAddress.replaceFirst("%.*", "");
                        ipv6List.add(ipv6);
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException("Failed to get network interfaces: " + e.getMessage());
        }
        
        Optional<String> max = ipv6List.stream().max(Comparator.comparingInt(String::length));
        String ipv6 = "";
        if (max.isPresent()){
            ipv6 = max.get();
        }
        
        log.info("IPv6 Address on wlo1: {}", ipv6);
        return ipv6;
    }
    
    public static String getLocalIpv6AddressByHttp(){
        String ipv6Address;
        String getIpv6Url = "https://6.ipw.cn";
        try  {
            String responseContent = HttpClientHelper.sendHttpGetRequest(getIpv6Url);
            Ipv6Util ipv6Util = new Ipv6Util();
            ipv6Address = ipv6Util.parseIpv6Address(responseContent);
            log.info("{}  获取的本机IPv6地址: {}", getIpv6Url, ipv6Address);
        } catch (Exception e) {
            ipv6Address = getLocalIpv6AddressByHostname();
            log.error("获取ipv6发生错误: {}", e.getMessage());
        }
        return ipv6Address;
    }
    
    private String parseIpv6Address(String content) {
        // 这里根据6.ipw.cn返回的实际内容格式编写解析逻辑
        // 假设地址在响应的首行
        return content.split("\n")[0];
    }
}