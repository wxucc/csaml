package cn.tedu.mall.search.test;


import cn.tedu.mall.pojo.search.entity.SpuForElastic;
import cn.tedu.mall.search.repository.SpuForElasticRepository;
import cn.tedu.mall.search.service.ISearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// 下面注解必须加!!!!
@SpringBootTest
public class SpuElasticTest {

    @Autowired
    private ISearchService searchService;
    @Test
    void loadData(){
        searchService.loadSpuByPage();
        System.out.println("ok");
    }

    @Autowired
    private SpuForElasticRepository repository;
    @Test
    void showData(){
        Iterable<SpuForElastic> spus=repository.findAll();
        spus.forEach(spu-> System.out.println(spu));
    }

    @Test
    void showTitle(){
        Iterable<SpuForElastic> spus=
                repository.querySpuForElasticsByTitleMatches("手机");
        spus.forEach(spu -> System.out.println(spu));
    }

    @Test
    void showQuery(){
        // 查询四个字段中包含指定关键字的方法
        //Iterable<SpuForElastic> spus=repository.querySearch("手机");
        Page spus=repository.querySearch("手机", PageRequest.of(1,2));
        spus.forEach(spu-> System.out.println(spu));
    }

}
