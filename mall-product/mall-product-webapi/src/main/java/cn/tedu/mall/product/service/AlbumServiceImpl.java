package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.AlbumMapper;
import cn.tedu.mall.product.mapper.PictureMapper;
import cn.tedu.mall.product.mapper.SpuMapper;
import cn.tedu.mall.pojo.product.dto.AlbumAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AlbumUpdateDTO;
import cn.tedu.mall.pojo.product.model.Album;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>相册业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SpuMapper spuMapper;

    @Override
    public Long addNew(AlbumAddNewDTO albumAddnewDTO) {
        log.debug("增加相册：{}", albumAddnewDTO);
        // 检查名称是否被占用
        String albumName = albumAddnewDTO.getName();
        Object checkNameQueryResult = albumMapper.getByName(albumName);
        if (checkNameQueryResult != null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增相册失败，相册名称(" + albumName + ")已存在！");
        }

        // 执行插入相册数据
        Album album = new Album();
        album.setName(albumName);
        album.setDescription(albumAddnewDTO.getDescription());
        album.setSort(albumAddnewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : albumAddnewDTO.getSort());
        log.info("新增相册数据：{}", album);
        int rows = albumMapper.insert(album);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增相册失败，服务器忙，请稍后再次尝试！");
        }
        return album.getId();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("删除相册，id={}", id);
        // 检查尝试删除的相册是否存在
        AlbumStandardVO result = albumMapper.getById(id);
        if (result == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "删除相册失败，尝试访问的数据不存在！");
        }

        // 检查此相册下是否包含图片
        int pictureCount = pictureMapper.countByAlbumId(id);
        if (pictureCount > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除相册失败，仍有图片关联到此相册，请先删除相关图片或解除关联后再次尝试！");
        }

        // 检查此相册是否已被关联到SPU
        int spuCount = spuMapper.countByAlbumId(id);
        if (spuCount > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除相册失败，此相册已被关联到SPU，请先删除相关SPU或解除关联后再次尝试！！");
        }

        // 执行删除
        log.debug("删除id为{}的相册数据", id);
        int rows = albumMapper.deleteById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除相册失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateById(Long id, AlbumUpdateDTO albumUpdateDTO) {
        log.debug("开始更新相册，id={}，新数据={}", id, albumUpdateDTO);
        // 检查尝试更新的数据是否存在
        AlbumStandardVO currentAlbum = albumMapper.getById(id);
        if (currentAlbum == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新相册信息失败，尝试访问的数据不存在！");
        }

        // 检查新名称是否冲突
        String albumNewName = albumUpdateDTO.getName();
        AlbumStandardVO checkNameQueryResult = albumMapper.getByName(albumNewName);
        if (checkNameQueryResult != null && !checkNameQueryResult.getId().equals(id)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "更新相册信息失败，相册名称(" + albumNewName + ")已存在！");
        }

        // 执行更新
        Album album = new Album();
        BeanUtils.copyProperties(albumUpdateDTO, album);
        album.setId(id);
        album.setName(albumUpdateDTO.getName());
        album.setDescription(albumUpdateDTO.getDescription());
        album.setSort(albumUpdateDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : albumUpdateDTO.getSort());
        log.debug("即将提交更新相册数据：{}", album);
        int rows = albumMapper.update(album);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新相册信息失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public AlbumStandardVO getById(Long id) {
        AlbumStandardVO albumStandardVO = albumMapper.getById(id);
        if (albumStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取相册详情失败，尝试访问的数据不存在！");
        }
        return albumStandardVO;
    }

    @Override
    public JsonPage<AlbumStandardVO> list(Integer page, Integer pageSize) {
        log.debug("查询相册列表，页码={}，每页记录数={}", page, pageSize);
        PageHelper.startPage(page, pageSize);
        List<AlbumStandardVO> albums = albumMapper.list();
        log.debug("查询分页相册列表：page={}，size={}", page, pageSize);
        return JsonPage.restPage(new PageInfo<>(albums));
    }

}
