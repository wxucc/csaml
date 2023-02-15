package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.AlbumAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AlbumUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;

/**
 * <p>相册业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface IAlbumService {

    /**
     * 增加相册
     *
     * @param albumAddnewDTO 新增的相册对象
     * @return 新增的相册id
     */
    Long addNew(AlbumAddNewDTO albumAddnewDTO);

    /**
     * 删除相册
     *
     * @param id 被删除的相册的id
     */
    void deleteById(Long id);

    /**
     * 更新相册
     *
     * @param id             被修改的相册的id
     * @param albumUpdateDTO 新的相关值的对象
     */
    void updateById(Long id, AlbumUpdateDTO albumUpdateDTO);

    /**
     * 根据相册id获取相册详情
     *
     * @param id 相册id
     * @return 匹配的相册详情，如果没有匹配的数据，将抛出异常
     */
    AlbumStandardVO getById(Long id);

    /**
     * 查询相册列表
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @return 相册的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<AlbumStandardVO> list(Integer page, Integer pageSize);

}
