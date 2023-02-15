package cn.tedu.mall.search.repository;

import cn.tedu.mall.pojo.search.entity.SpuForElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// SpuForElastic实体类操作ES的持久层接口
// 继承的父接口ElasticsearchRepository,对应的实体类基本的增删改查方法
@Repository
public interface SpuForElasticRepository extends
                            ElasticsearchRepository<SpuForElastic,Long> {

    // 查询title字段中包含指定关键字的spu数据
    Iterable<SpuForElastic> querySpuForElasticsByTitleMatches(String title);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"should\": [\n" +
            "        { \"match\": { \"name\":  \"?0\" }},\n" +
            "        { \"match\": { \"title\": \"?0\"}},\n" +
            "        { \"match\": { \"description\": \"?0\"}},\n" +
            "        { \"match\": { \"category_name\": \"?0\"}}\n" +
            "      ]\n" +
            "    }" +
            "}")
    // 上面使用了指定搜索语句的方式来进行查询,下面的方法名就可以随意定义了
    Page<SpuForElastic> querySearch(String keyword, Pageable pageable);
}
