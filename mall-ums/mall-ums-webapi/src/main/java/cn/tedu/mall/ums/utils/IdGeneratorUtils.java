package cn.tedu.mall.ums.utils;

import org.springframework.web.client.RestTemplate;

public class IdGeneratorUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Long getDistributeId(String key) {
        String url = "http://127.0.0.1:9090/api/segment/get/" + key;
        return restTemplate.getForObject(url, Long.class);
    }
}
