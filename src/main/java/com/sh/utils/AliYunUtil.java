package com.sh.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.teaopenapi.models.OpenApiRequest;
import com.sh.model.config.AliYunConfigProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: AliYunUtil
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 13:42
 * @Author: SH
 */
@Component
public class AliYunUtil {
    
    private static  String ACCESS_KEY_ID ;
    private static  String ACCESS_KEY_SECRET ;
    private static  String ENDPOINT ;
    
    @Resource
    private AliYunConfigProperties aliYunConfigProperties;
    
    @PostConstruct
    private void init(){
        ACCESS_KEY_ID = aliYunConfigProperties.getAccessKeyId();
        ACCESS_KEY_SECRET = aliYunConfigProperties.getAccessKeySecret();
        ENDPOINT = aliYunConfigProperties.getEndpoint();
    }
    /**
     * 使用AK&SK初始化账号Client
     * @return Client
     * @throws Exception
     */
    private static com.aliyun.teaopenapi.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config;
        if (StrUtil.isBlank(accessKeyId) || StrUtil.isBlank(accessKeySecret)) {
            config = new com.aliyun.teaopenapi.models.Config()
                    .setAccessKeyId(ACCESS_KEY_ID)
                    .setAccessKeySecret(ACCESS_KEY_SECRET);
        } else {
            config = new com.aliyun.teaopenapi.models.Config()
                    .setAccessKeyId(accessKeyId)
                    .setAccessKeySecret(accessKeySecret);
        }
        config.endpoint = ENDPOINT;
        return new com.aliyun.teaopenapi.Client(config);
    }
    
    /**
     * API 相关
     * @return OpenApi.Params
     */
    private static com.aliyun.teaopenapi.models.Params createApiInfo(String action,String method) {
        com.aliyun.teaopenapi.models.Params params = new com.aliyun.teaopenapi.models.Params()
                // 接口名称
                .setAction(action)
                // 接口版本
                .setVersion("2015-01-09")
                // 接口协议
                .setProtocol("HTTPS")
                // 接口 HTTP 方法
                .setMethod(method)
                .setAuthType("AK")
                .setStyle("RPC")
                // 接口 PATH
                .setPathname("/")
                // 接口请求体内容格式
                .setReqBodyType("json")
                // 接口响应体内容格式
                .setBodyType("json");
        return params;
    }
    
    
    public static <T>Optional<T> sendPost(Object paramDto, Class<T> clazz) {
        Map<String, ?> map;
        try {
            map = toPost(paramDto, "POST");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(JSON.parseObject(JSON.toJSONString(map),clazz));
    }
    
    public static <T>Optional<T> sendPost(Object paramDto, Class<T> clazz, String accessKeyId, String accessKeySecret) {
        Map<String, ?> map;
        try {
            map = toPost(paramDto, "POST",accessKeyId,accessKeySecret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(JSON.parseObject(JSON.toJSONString(map),clazz));
    }
    private static Map<String, ?> toPost(Object paramDto,String method) throws Exception {
        Map<String, Object> map = BeanUtils.objectToMap(paramDto);
        com.aliyun.teaopenapi.Client client = createClient(null, null);
        com.aliyun.teaopenapi.models.Params params = createApiInfo(String.valueOf(BeanUtils.getFieldValue(paramDto, "action")),method);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.teaopenapi.models.OpenApiRequest request = new OpenApiRequest().setQuery(com.aliyun.openapiutil.Client.query(map));
        return  client.callApi(params, request, runtime);
    }
    
    private static Map<String, ?> toPost(Object paramDto,String method,String accessKeyId,String accessKeySecret) throws Exception {
        Map<String, Object> map = BeanUtils.objectToMap(paramDto);
        com.aliyun.teaopenapi.Client client = createClient(accessKeyId, accessKeySecret);
        com.aliyun.teaopenapi.models.Params params = createApiInfo(String.valueOf(BeanUtils.getFieldValue(paramDto, "action")),method);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.teaopenapi.models.OpenApiRequest request = new OpenApiRequest().setQuery(com.aliyun.openapiutil.Client.query(map));
        return  client.callApi(params, request, runtime);
    }
    
    
}
