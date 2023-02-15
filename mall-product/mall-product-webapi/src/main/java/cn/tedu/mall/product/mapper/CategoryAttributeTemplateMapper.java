package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.CategoryAttributeTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>类别与属性模板关联Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface CategoryAttributeTemplateMapper {

    /**
     * 插入类别与属性模板关联
     *
     * @param categoryAttributeTemplate 类别与属性模板关联对象
     * @return 受影响的行数
     */
    int insert(CategoryAttributeTemplate categoryAttributeTemplate);

    /**
     * 根据类别id统计关联数据的数量
     *
     * @param categoryId 类别id
     * @return 关联数据的数量
     */
    int countByCategoryId(Long categoryId);

}
