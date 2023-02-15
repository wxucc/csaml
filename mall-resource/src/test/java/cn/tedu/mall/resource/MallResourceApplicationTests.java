package cn.tedu.mall.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class MallResourceApplicationTests {

    @Test
    void contextLoads() {
        String value = "";
        String regExp = "(?i)^[1-9]\\d*(KB|MB|GB)?$";

        value = "1";
        Assertions.assertTrue(value.matches(regExp));
        value = "1234";
        Assertions.assertTrue(value.matches(regExp));
        value = "1kb";
        Assertions.assertTrue(value.matches(regExp));
        value = "1Kb";
        Assertions.assertTrue(value.matches(regExp));
        value = "1kB";
        Assertions.assertTrue(value.matches(regExp));
        value = "1KB";
        Assertions.assertTrue(value.matches(regExp));
        value = "1mb";
        Assertions.assertTrue(value.matches(regExp));
        value = "1gb";
        Assertions.assertTrue(value.matches(regExp));
        value = "10086kb";
        Assertions.assertTrue(value.matches(regExp));
        value = "1aKB";
        Assertions.assertFalse(value.matches(regExp));
        value = "1.2mb";
        Assertions.assertFalse(value.matches(regExp));
        value = "-1kb";
        Assertions.assertFalse(value.matches(regExp));
    }

}
