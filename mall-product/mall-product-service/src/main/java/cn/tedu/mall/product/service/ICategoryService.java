package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.CategoryAddNewDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateBaseInfoDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>类别业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface ICategoryService {

    /**
     * 增加类别
     *
     * @param categoryDTO 新增的类别对象
     */
    @Transactional
    Long addNew(CategoryAddNewDTO categoryDTO);

    /**
     * 根据id删除类别
     *
     * @param id 被删除的类别的id
     */
    @Transactional
    void deleteById(Long id);

    /**
     * 启用类别
     *
     * @param id 类别id
     */
    void setEnableById(Long id);

    /**
     * 禁用类别
     *
     * @param id 类别id
     */
    void setDisableById(Long id);

    /**
     * 显示类别
     *
     * @param id 类别id
     */
    void setDisplayById(Long id);

    /**
     * 隐藏（不显示）类别
     *
     * @param id 类别id
     */
    void setHiddenById(Long id);

    /**
     * 更新类别的基本信息
     *
     * @param id                        类别id
     * @param categoryUpdateBaseInfoDTO 封装了类别基本信息的对象
     */
    void updateBaseInfoById(Long id, CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO);

    /**
     * 更新类别的全部信息
     *
     * @param id                        被修改的类别的id
     * @param categoryUpdateFullInfoDTO 新的相关值的对象
     */
    void updateFullInfoById(Long id, CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO);

    /**
     * 根据类别id获取类别详情
     *
     * @param id 类别id
     * @return 匹配的类别详情，如果没有匹配的数据，将抛出异常
     */
    CategoryStandardVO getById(Long id);

    /**
     * 查询类别列表
     *
     * @param brandId  品牌id
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 类别的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<CategoryStandardVO> listByBrand(Long brandId, Integer pageNum, Integer pageSize);

    /**
     * 查询类别列表
     *
     * @param parentId 父级类别id
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 类别的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<CategoryStandardVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);

}
