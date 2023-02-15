package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.SpuAddNewDTO;
import cn.tedu.mall.pojo.product.dto.SpuUpdateDTO;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>SPU（Standard Product Unit）业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface ISpuService {

    /**
     * 增加SPU
     *
     * @param spuAddNewDTO 新增的SPU对象
     */
    @Transactional
    void addNew(SpuAddNewDTO spuAddNewDTO);

    /**
     * 更新SPU
     *
     * @param id           被修改的SPU的id
     * @param spuUpdateDTO 新的相关值的对象
     */
    void updateById(Long id, SpuUpdateDTO spuUpdateDTO);

    /**
     * 通过审核SPU
     *
     * @param id SPU id
     */
    void passCheck(Long id);

    /**
     * 同步SPU库存值（用于增减SKU或SKU的库存变化后SPU库存未更新的情况）
     *
     * @param id SPU id
     */
    void synchroniseStock(Long id);

    /**
     * 同步SPU价格（用于增减SKU或SKU的变化变化后SPU价格未更新的情况）
     *
     * @param id SPU id
     */
    void synchronisePrice(Long id);

    /**
     * 根据SPU id查询SPU详情
     *
     * @param id SPU id
     * @return 匹配的SPU详情，如果没有匹配的数据，则返回null
     */
    SpuStandardVO getById(Long id);

    /**
     * 查询SPU列表
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @return SPU的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<SpuListItemVO> list(Integer page, Integer pageSize);

    /**
     * 从数据库中搜索SPU列表
     *
     * @param spuQuery 封装了搜索条件的对象
     * @param page     页码
     * @param pageSize 每页记录数
     * @return SPU的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<SpuListItemVO> listFromDB(SpuQuery spuQuery, Integer page, Integer pageSize);

    /**
     * 从ElasticSearch中搜索SPU列表
     *
     * @param spuQuery 封装了搜索条件的对象
     * @param page     页码
     * @param pageSize 每页记录数
     * @return SPU的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<SpuStandardVO> listFromES(SpuQuery spuQuery, Integer page, Integer pageSize);

}
