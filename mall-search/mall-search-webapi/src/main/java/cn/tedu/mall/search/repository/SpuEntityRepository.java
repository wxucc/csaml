package cn.tedu.mall.search.repository;

import cn.tedu.mall.pojo.search.entity.SpuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuEntityRepository extends ElasticsearchRepository<SpuEntity,Long> {
    //要实现根据用户输入的关键字,查询ES中的商品列表
    //因为search-test字段并没有在S谱Entity中声明,所以不能用方法名查询,只能使用查询语句

    @Query("{\"match\":{\"search-test\":{\"query\":\"?0\"}}}")
    Page<SpuEntity> querySearchByTest(String keyword, Pageable pageable);
}
