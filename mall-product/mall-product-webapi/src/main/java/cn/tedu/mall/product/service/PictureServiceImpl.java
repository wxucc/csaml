package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.dto.PictureAddNewDTO;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.AlbumMapper;
import cn.tedu.mall.product.mapper.PictureMapper;
import cn.tedu.mall.pojo.product.dto.PictureAddNewBatchDTO;
import cn.tedu.mall.pojo.product.dto.PictureUploadBatchDTO;
import cn.tedu.mall.pojo.product.dto.PictureUploadSingleDTO;
import cn.tedu.mall.pojo.product.model.Picture;
import cn.tedu.mall.pojo.product.vo.PictureSimpleVO;
import cn.tedu.mall.pojo.product.vo.PictureStandardVO;
import cn.tedu.mall.product.utils.PictureUploadUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>图片业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class PictureServiceImpl implements IPictureService {

    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private AlbumMapper albumMapper;

    private PictureUploadUtils pictureUploadUtils = new PictureUploadUtils();

    @Override
    public void addNew(PictureAddNewDTO pictureAddNewDTO) {
        // 检查URL是否已存在（各图片的URL应该是唯一的）
        String url = pictureAddNewDTO.getUrl();
        PictureStandardVO checkExistQueryResult = pictureMapper.getByUrl(url);
        if (checkExistQueryResult != null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增图片记录失败，此URL对应的记录已存在！");
        }

        // 检查相册是否存在
        Long albumId = pictureAddNewDTO.getAlbumId();
        Object checkAlbumExistQueryResult = albumMapper.getById(albumId);
        if (checkAlbumExistQueryResult == null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增图片记录失败，选择的相册不存在，可能已经被删除！");
        }

        // 处理封面（此图片是否是封面）
        int pictureCountOfAlbum = pictureMapper.countByAlbumId(albumId);
        int cover = pictureCountOfAlbum == 0 ? 1 : 0;

        // 执行新增
        Picture picture = new Picture();
        BeanUtils.copyProperties(pictureAddNewDTO, picture);
        picture.setCover(cover);
        picture.setSort(pictureAddNewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : pictureAddNewDTO.getSort());
        int rows = pictureMapper.insert(picture);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增图片记录失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void bindBatchToAlbum(PictureAddNewBatchDTO[] pictureDTOS) {
        List<Picture> pictures = new ArrayList<>();
        for (PictureAddNewBatchDTO pictureDTO : pictureDTOS) {
            Picture picture = new Picture();
            BeanUtils.copyProperties(pictureDTO, picture);
            pictures.add(picture);
        }
        pictureMapper.insertBatch(pictures);
    }

    @Override
    public PictureSimpleVO uploadSingle(PictureUploadSingleDTO pictureUploadSingleDTO) {
        PictureSimpleVO pictureSimpleVO = pictureUploadUtils.uploadPicture(pictureUploadSingleDTO);
        return pictureSimpleVO;
    }

    @Override
    public PictureSimpleVO uploadSingleToAlbum(Long albumId, PictureUploadSingleDTO pictureUploadSingleDTO) {
        // 执行上传
        PictureSimpleVO pictureSimpleVO = pictureUploadUtils.uploadPicture(pictureUploadSingleDTO);

        // 如果这是相册中尚无封面，则将此图片设置为封面
        PictureStandardVO checkCoverQueryResult = pictureMapper.getCoverPictureByAlbumId(albumId);
        Integer cover = checkCoverQueryResult == null ? 1 : 0;

        // 向图片表中插入数据
        Picture picture = new Picture();
        picture.setAlbumId(albumId);
        picture.setWidth(pictureSimpleVO.getWidth());
        picture.setHeight(pictureSimpleVO.getHeight());
        picture.setUrl(pictureSimpleVO.getUrl());
        picture.setSort(DataCommonConst.SORT_DEFAULT);
        picture.setCover(cover);
        int rows = pictureMapper.insert(picture);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "上传图片到相册，服务器忙，请稍后再次尝试！");
        }
        return pictureSimpleVO;
    }

    @Override
    public List<PictureSimpleVO> uploadBatch(PictureUploadBatchDTO pictureUploadBatchDTO) {
        List<PictureSimpleVO> pictureSimpleVOList = pictureUploadUtils.uploadPictures(pictureUploadBatchDTO);
        return pictureSimpleVOList;
    }

    @Override
    public void setCover(Long id) {
        // 查询尝试操作的数据
        PictureStandardVO currentPicture = pictureMapper.getById(id);

        // 检查尝试操作的数据是否存在
        if (currentPicture == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "设置封面失败，尝试访问的数据不存在！");
        }

        // 检查当前图片是否已经是封面
        if (currentPicture.getCover() == 1) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "设置封面失败，当前图片已经是所在相册的封面！");
        }

        // 将图片所在相册的所有图片设置为"非封面"
        int rows = pictureMapper.updateNonCoverByAlbumId(currentPicture.getAlbumId());
        if (rows < 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "设置封面失败，服务器忙，请稍后再次尝试！");
        }

        // 将当前图片设置为封面
        rows = pictureMapper.updateCoverById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "设置封面失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public JsonPage<PictureStandardVO> listByAlbumId(Long albumId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PictureStandardVO> pictureList = pictureMapper.listByAlbumId(albumId);
        return JsonPage.restPage(new PageInfo<>(pictureList));
    }

}
