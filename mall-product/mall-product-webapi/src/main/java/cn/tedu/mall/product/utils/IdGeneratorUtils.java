package cn.tedu.mall.product.utils;

import org.springframework.web.client.RestTemplate;

public class IdGeneratorUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String LEAF_URL = "http://127.0.0.1:9090/api/segment/get/";

    public static Long getDistributeId(Key key) {
        String url = LEAF_URL + key.getValue();
        return restTemplate.getForObject(url, Long.class);
    }

    public enum Key {

        SPU("spu"),
        SKU("sku");

        private String value;

        Key(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

}
