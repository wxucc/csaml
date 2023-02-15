package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Album;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>相册Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface AlbumMapper {

    /**
     * 新增相册
     *
     * @param album 新增的相册对象
     * @return 受影响的行数
     */
    int insert(Album album);

    /**
     * 根据id删除相册
     *
     * @param id 被删除的相册的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改相册
     *
     * @param album 封装了被修改的相册的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int update(Album album);

    /**
     * 根据相册id查询相册详情
     *
     * @param id 相册id
     * @return 匹配的相册详情，如果没有匹配的数据，则返回null
     */
    AlbumStandardVO getById(Long id);

    /**
     * 根据相册名称查询相册详情
     *
     * @param name 相册名称
     * @return 匹配的相册详情，如果没有匹配的数据，则返回null
     */
    AlbumStandardVO getByName(String name);

    /**
     * 查询相册列表
     *
     * @return 相册的列表，如果无相册记录，则返回长度为0的列表
     */
    List<AlbumStandardVO> list();

}
