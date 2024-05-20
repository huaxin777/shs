package com.sh.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName: HttpClientHelper
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/27 15:24
 * @Author: SH
 */
@Slf4j
public class HttpClientHelper {
	// 连接超时时间（毫秒）
	private static final int CONNECTION_TIMEOUT_MS = 5000;
	// 读取超时时间（毫秒）
	private static final int READ_TIMEOUT_MS = 10000;
	
	/**
	 * 发送GET请求，并返回HttpEntity
	 *
	 * @param url 请求的URL
	 * @return String 响应内容
	 * @throws IOException 如果请求过程中出现IO错误
	 */
	public static String sendHttpGetRequest(String url) throws IOException {
        log.info("发送GET请求: {}", url);
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// 构建GET请求
			HttpGet httpGet = new HttpGet(url);
			
			// 设置请求超时参数
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(CONNECTION_TIMEOUT_MS)
					.setSocketTimeout(READ_TIMEOUT_MS)
					.build();
			httpGet.setConfig(requestConfig);
			
			// 执行请求
			try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
				// 检查响应状态码
				if (response.getStatusLine().getStatusCode() == 200) {
					// 如果状态码为200（OK），返回响应实体
					return EntityUtils.toString(response.getEntity(), "UTF-8");
				} else {
					// 若非200，可以抛出异常或者处理错误
					throw new IOException("请求失败，状态码: " + response.getStatusLine().getStatusCode());
				}
			}
		}
	}
}
