package cn.tedu.mall.search.utils;

import org.springframework.web.client.RestTemplate;

public class IdGeneratorUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Long getDistributeId(String key) {
        String url = "http://127.0.0.1:8080/api/segment/get/" + key;
        return restTemplate.getForObject(url, Long.class);
    }
}
