package cn.tedu.mall.front.service.test;

import cn.tedu.mall.front.MallFrontWebApiApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallFrontWebApiApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    /*@Test
    public void save(){
        String key="key1";
        FrontCategoryTreeVO frontCategoryTreeVO=new FrontCategoryTreeVO();
        redisTemplate.boundValueOps(key).set(frontCategoryTreeVO);
        System.out.println(redisTemplate.boundValueOps(key).get());
    }*/
}
