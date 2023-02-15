package cn.tedu.mall.resource.controller;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.resource.util.FileUploadUtils;
import cn.tedu.mall.resource.util.ImageUtils;
import cn.tedu.mall.resource.vo.ImageFileVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传的控制器
 */
@Api(tags = "1. 文件上传")
@RequestMapping("/upload")
@RestController
@Slf4j
public class FileUploadController {

    @Value("${custom.file-upload.server-local-base-path}")
    private String basePath;
    @Value("${custom.file-upload.resource-host}")
    private String resourceHost;

    @Value("${custom.file-upload.picture.dir-name}")
    private String pictureDirName;
    @Value("${custom.file-upload.picture.size-limit}")
    private String pictureSizeLimit;
    @Value("${custom.file-upload.picture.content-type}")
    private List<String> pictureContentType;

    @Value("${custom.file-upload.category-icon.dir-name}")
    private String categoryIconDirName;
    @Value("${custom.file-upload.category-icon.size-limit}")
    private String categoryIconSizeLimit;
    @Value("${custom.file-upload.category-icon.content-type}")
    private List<String> categoryIconContentType;

    @Value("${custom.file-upload.brand-logo.dir-name}")
    private String brandLogoDirName;
    @Value("${custom.file-upload.brand-logo.size-limit}")
    private String brandLogoSizeLimit;
    @Value("${custom.file-upload.brand-logo.content-type}")
    private List<String> brandLogoContentType;

    /**
     * 上传品牌LOGO
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation("上传品牌LOGO")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "brandNamePinyin", value = "品牌名称的拼音（不区别大小写）", required = true)
    )
    @PostMapping(value = "/brand-logo")
    public JsonResult<ImageFileVO> uploadBrandLogo(@RequestParam MultipartFile file, String brandNamePinyin) {
        String imageFileName = FileUploadUtils.generateFileNameByCurrentDateTime();
        return uploadImage(file, brandLogoSizeLimit, brandLogoContentType, brandLogoDirName, brandNamePinyin.toLowerCase(), imageFileName);
    }

    /**
     * 上传类别图标
     */
    @ApiOperationSupport(order = 11)
    @ApiOperation("上传类别图标")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "categoryNamePinyin", value = "类别名称的拼音（不区别大小写）", required = true)
    )
    @PostMapping(value = "/category-icon")
    public JsonResult<ImageFileVO> uploadCategoryIcon(@RequestParam MultipartFile file, String categoryNamePinyin) {
        String imageFileName = FileUploadUtils.generateFileNameByCurrentDateTime();
        return uploadImage(file, categoryIconSizeLimit, categoryIconContentType, categoryIconDirName, categoryNamePinyin.toLowerCase(), imageFileName);
    }

    /**
     * 上传商品图片（单张）
     */
    @ApiOperationSupport(order = 12)
    @ApiOperation("上传商品图片（单张）")
    @PostMapping(value = "/picture/single")
    public JsonResult<ImageFileVO> uploadSinglePicture(@RequestParam MultipartFile file) {
        String subDirName = FileUploadUtils.generateDirNameByDate();
        String imageFileName = FileUploadUtils.generateFileNameByCurrentDateTime();
        return uploadImage(file, pictureSizeLimit, pictureContentType, pictureDirName, subDirName, imageFileName);
    }

    /**
     * 上传图片
     *
     * @param file                上传的图片信息
     * @param sizeLimit           限制的图片大小，必须是纯数字，或以KB/MB/GB为单位
     * @param contentType         限制的文件类型
     * @param targetParentDirName 目标文件的父级文件夹名，例如image、brand_logo等
     * @param targetSubDirName    目标文件的子级文件夹名，可多层级，不同的图片的子级文件夹设计可能不同
     * @param targetFileName      目标文件名，不含扩展名
     * @return 成功上传的图片文件详情
     */
    private JsonResult<ImageFileVO> uploadImage(MultipartFile file, String sizeLimit, List<String> contentType, String targetParentDirName, String targetSubDirName, String targetFileName) {
        // 检查是否提交了上传数据
        if (file == null || file.isEmpty()) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "上传文件失败，请提交文件！");
        }
        // 检查文件类型是否匹配
        if (!contentType.contains(file.getContentType())) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "上传文件失败，文件类型受限，当前文件类型：" + file.getContentType() + "，允许的类型清单：" + contentType + "！");
        }
        // 检查上传的文件大小是否超出了限制
        long sizeLimitValue = FileUploadUtils.getSizeLimitValue(sizeLimit);
        if (file.getSize() > sizeLimitValue) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "上传文件失败，文件大小超出了限制，当前文件大小：" + file.getSize() + "，限制值：" + sizeLimit + "！");
        }
        // 准备保存上传文件的文件夹
        File imageBaseDir = new File(basePath, targetParentDirName);
        File imageUploadDir = new File(imageBaseDir, targetSubDirName);
        if (!imageUploadDir.exists()) {
            imageUploadDir.mkdirs();
        }
        // 准备目标文件的全名
        String suffix = FileUploadUtils.getSuffixByOriginalFileName(file.getOriginalFilename());
        String fullFileName = targetFileName + suffix;
        // 确定目标文件
        File destFile = new File(imageUploadDir, fullFileName);
        // 执行保存
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "上传文件失败，服务器忙，请稍后再次尝试！");
        }
        // 读取保存的图片文件信息
        ImageUtils.ImageInfo imageInfo = null;
        try {
            imageInfo = ImageUtils.getImageInfo(destFile);
        } catch (IOException e) {
            destFile.delete();
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "上传文件失败，无法读取到图片关键信息，请重新上传！");
        }
        // 准备图片的HTTP URL
        String imageUrl = resourceHost + targetParentDirName + "/" + targetSubDirName + "/" + fullFileName;
        // 准备响应
        ImageFileVO imageFileVO = new ImageFileVO();
        imageFileVO.setUrl(imageUrl);
        imageFileVO.setContentType(file.getContentType());
        imageFileVO.setSize(file.getSize());
        imageFileVO.setWidth(imageInfo.getWidth());
        imageFileVO.setHeight(imageInfo.getHeight());
        // 执行响应
        return JsonResult.ok(imageFileVO);
    }


}
