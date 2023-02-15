package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.AlbumAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AlbumUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AlbumAddNewResultVO;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.IAlbumService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>相册控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "4. 相册管理模块")
@RestController
@RequestMapping(value = "/pms/albums", produces = "application/json;charset=utf-8")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    /**
     * 新增相册
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "新增相册", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<AlbumAddNewResultVO> addNew(@Valid AlbumAddNewDTO albumAddDTO) {
        Long id = albumService.addNew(albumAddDTO);
        AlbumAddNewResultVO albumAddVO = new AlbumAddNewResultVO();
        albumAddVO.setId(id);
        return JsonResult.ok(albumAddVO);
    }

    /**
     * 删除相册
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "删除相册", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{albumId:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long albumId) {
        albumService.deleteById(albumId);
        return JsonResult.ok();
    }

    /**
     * 更新相册
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新相册", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{albumId:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long albumId, @Valid AlbumUpdateDTO albumUpdateDTO) {
        albumService.updateById(albumId, albumUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 查询相册详情
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询相册详情", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册id", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<AlbumStandardVO> getById(@PathVariable Long id) {
        return JsonResult.ok(albumService.getById(id));
    }

    /**
     * 查询相册列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "查询相册列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<AlbumStandardVO>> list(
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<AlbumStandardVO> albums = albumService.list(page, pageSize);
        return JsonResult.ok(albums);
    }

}
