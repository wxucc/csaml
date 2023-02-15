package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.dto.*;
import cn.tedu.mall.pojo.product.vo.AlbumAddNewResultVO;
import cn.tedu.mall.pojo.product.vo.PictureSimpleVO;
import cn.tedu.mall.pojo.product.vo.PictureStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.IPictureService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>图片控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "5. 图片管理模块")
@RestController
@RequestMapping("/pms/pictures")
public class PictureController {

    @Autowired
    private IPictureService pictureService;

    /**
     * 新增图片记录
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "新增图片记录", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid PictureAddNewDTO pictureAddNewDTO) {
        pictureService.addNew(pictureAddNewDTO);
        return JsonResult.ok();
    }

    /**
     * 将图片设置为封面
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "将图片设置为封面", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图片id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/set-cover")
    public JsonResult<Void> setCover(@PathVariable Long id) {
        pictureService.setCover(id);
        return JsonResult.ok();
    }

    /**
     * 根据相册查询图片列表
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "根据相册查询图片列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<PictureStandardVO>> listPictures(
            @RequestParam Long albumId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<PictureStandardVO> pictures = pictureService.listByAlbumId(albumId, page, pageSize);
        return JsonResult.ok(pictures);
    }

    // ==============  Deprecated  ============== //

    /**
     * 上传单张图片
     */
    @Deprecated
    @ApiOperationSupport(order = 90)
    @ApiOperation(value = "上传单张图片", notes = "上传单张图片，将返回图片的URL、宽度、高度，需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/upload-single")
    public JsonResult<PictureSimpleVO> uploadSingle(PictureUploadSingleDTO pictureUploadSingleDTO) {
        PictureSimpleVO pictureSimpleVO = pictureService.uploadSingle(pictureUploadSingleDTO);
        return JsonResult.ok(pictureSimpleVO);
    }

    /**
     * 上传图片到相册
     */
    @Deprecated
    @ApiOperationSupport(order = 91)
    @ApiOperation(value = "上传图片到相册", notes = "上传图片到相册，将返回图片的URL、宽度、高度，需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/upload-single-to-album")
    public JsonResult<PictureSimpleVO> uploadSingleToAlbum(@RequestParam Long albumId, PictureUploadSingleDTO pictureUploadSingleDTO) {
        PictureSimpleVO pictureSimpleVO = pictureService.uploadSingleToAlbum(albumId, pictureUploadSingleDTO);
        return JsonResult.ok(pictureSimpleVO);
    }

    /**
     * 批量上传图片
     */
    @Deprecated
    @ApiOperationSupport(order = 92)
    @ApiOperation(value = "批量上传图片", notes = "批量上传图片，返回多个图片的URL、宽度、高度，需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/upload-batch")
    public JsonResult<List<PictureSimpleVO>> uploadBatch(PictureUploadBatchDTO pictureUploadBatchDTO) {
        List<PictureSimpleVO> pictureSimpleVOS = pictureService.uploadBatch(pictureUploadBatchDTO);
        return JsonResult.ok(pictureSimpleVOS);
    }

    /**
     * 批量将图片插入相册
     */
    @Deprecated
    @ApiOperationSupport(order = 95)
    @ApiOperation(value = "批量将图片插入相册", notes = "此接口已声明为【已过期】，且【不提供】服务！")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/bind-batch-to-album")
    public JsonResult<Void> bindBatchToAlbum(PictureAddNewBatchDTO[] pictureAddnewBatchDTO) {
        boolean enable = false;
        if (!enable) {
            throw new CoolSharkServiceException(ResponseCode.FORBIDDEN, "此接口已声明为【已过期】，且【不提供】服务！");
        }
        pictureService.bindBatchToAlbum(pictureAddnewBatchDTO);
        return JsonResult.ok();
    }

}
