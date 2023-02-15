package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.BrandAddNewDTO;
import cn.tedu.mall.pojo.product.dto.BrandUpdateDTO;
import cn.tedu.mall.pojo.product.vo.BrandStandardVO;

/**
 * <p>品牌业务接口/p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface IBrandService {

    /**
     * 增加品牌
     *
     * @param brandDTO 新增的品牌对象
     */
    void addNew(BrandAddNewDTO brandDTO);

    /**
     * 删除品牌
     *
     * @param id 被删除的品牌的id
     */
    void deleteById(Long id);

    /**
     * 更新品牌
     *
     * @param id       被修改的品牌的id
     * @param brandDTO 新的相关值的对象
     */
    void updateFullInfoById(Long id, BrandUpdateDTO brandDTO);

    /**
     * 根据品牌id获取品牌详情
     *
     * @param id 品牌id
     * @return 匹配的品牌详情，如果没有匹配的数据，将抛出异常
     */
    BrandStandardVO getById(Long id);

    /**
     * 查询品牌列表
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @return 品牌的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<BrandStandardVO> list(Integer page, Integer pageSize);

    /**
     * 利用类别id查询分页数据的品牌列表
     *
     * @param categoryId 类别id
     * @param page       页码
     * @param pageSize   每页记录数
     * @return 品牌的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<BrandStandardVO> listByCategoryId(Long categoryId, Integer page, Integer pageSize);

}
