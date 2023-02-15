package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Picture;
import cn.tedu.mall.pojo.product.vo.PictureStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>图片Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface PictureMapper {

    /**
     * 插入图片
     *
     * @param picture 图片
     * @return 受影响的行数
     */
    int insert(Picture picture);

    /**
     * 批量插入图片
     *
     * @param pictures 插入的图片集合
     * @return 受影响的行数
     */
    @Deprecated
    int insertBatch(List<Picture> pictures);

    /**
     * 根据id删除图片
     *
     * @param id 被删除的图片的id
     * @return 受影响的行数
     */
    @Deprecated
    int deleteById(Long id);

    /**
     * 将指定的图片设置为封面
     *
     * @param id 图片id
     * @return 受影响的行数
     */
    int updateCoverById(Long id);

    /**
     * 将指定相册中的所有图片设置为"非封面"
     *
     * @param albumId 相册id
     * @return 受影响的行数
     */
    int updateNonCoverByAlbumId(Long albumId);

    /**
     * 统计某相册中的图片数量
     *
     * @param albumId 相册id
     * @return 相册中的图片数据，如果相册中没有图片，则返回0
     */
    int countByAlbumId(Long albumId);

    /**
     * 根据图片id查询图片详情
     *
     * @param id 图片id
     * @return 匹配的图片详情，如果没有匹配的数据，则返回null
     */
    PictureStandardVO getById(Long id);

    /**
     * 根据图片url查询图片详情
     *
     * @param url 图片url
     * @return 匹配的图片详情，如果没有匹配的数据，则返回null
     */
    PictureStandardVO getByUrl(String url);

    /**
     * 根据相册id查询封面图片详情
     *
     * @param albumId 相册id
     * @return 匹配的图片详情，如果没有匹配的数据，则返回null
     */
    PictureStandardVO getCoverPictureByAlbumId(Long albumId);

    /**
     * 根据相册id查询图片URL列表
     *
     * @param albumId 相册id
     * @return 图片URL列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<String> listUrlsByAlbumId(Long albumId);

    /**
     * 根据相册id查询图片列表
     *
     * @param albumId 相册id
     * @return 图片列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<PictureStandardVO> listByAlbumId(Long albumId);

}
