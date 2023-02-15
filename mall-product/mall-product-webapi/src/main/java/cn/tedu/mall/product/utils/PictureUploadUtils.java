package cn.tedu.mall.product.utils;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.dto.PictureUploadBatchDTO;
import cn.tedu.mall.pojo.product.dto.PictureUploadSingleDTO;
import cn.tedu.mall.pojo.product.vo.PictureSimpleVO;
import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 存储图片，生成URL并返回Picture对象
 */
@Component
@Slf4j
public class PictureUploadUtils {

    @Value("${custom.file-upload.server-local-path}")
    private String fileUploadServerLocalPath;
    @Value("${custom.file-upload.resource-host}")
    private String fileUploadResourceHost;

    public List<PictureSimpleVO> uploadPictures(PictureUploadBatchDTO pictureUploadBatchDTO) {
        List<PictureSimpleVO> pictureSimpleVOList = new ArrayList<>();
        MultipartFile[] pictures = pictureUploadBatchDTO.getPictures();
        for (MultipartFile picture : pictures) {
            PictureSimpleVO pictureVO = new PictureSimpleVO();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(picture.getInputStream());
            } catch (IOException e) {
                log.info("图片转化成bufferImage失败,可能不是图片文件");
                e.printStackTrace();
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "当前文件不是图片文件，无法转化图片存储");
            }
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            //设置参数
            pictureVO.setWidth(width);
            pictureVO.setHeight(height);
            //解析名称
            String picName = picture.getOriginalFilename();
            if (StringUtils.isEmpty(picName)) {
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片原名称为空");
            }
            int index = picName.lastIndexOf(".");
            if (index == -1) {
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片后缀缺失");
            }
            String suffix = picName.substring(index).toLowerCase();
            if (!suffix.matches(".(png|jpg|jpeg|gif|img)")) {
                log.info("图片解析后缀为:" + suffix);
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片后缀不规范，或非图片文件");
            }
            //图片重命名
            String picNewName = UUID.randomUUID().toString() + suffix;
            //生成多级路径
            String path = generatorPath(pictureUploadBatchDTO.getHead());
            File dir = new File(fileUploadServerLocalPath, path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //上传写入
            try {
                picture.transferTo(new File(dir, picNewName));
            } catch (IOException e) {
                log.debug("当前图片:" + picName + "上传存储失败", e);
            }
            String url = fileUploadResourceHost + path + picNewName;
            pictureVO.setUrl(url);
            pictureSimpleVOList.add(pictureVO);
        }
        return pictureSimpleVOList;
    }

    public PictureSimpleVO uploadPicture(PictureUploadSingleDTO pictureDTO) {
        PictureSimpleVO pictureVO = new PictureSimpleVO();
        //验证图片是否是图片文件
        BufferedImage bufferedImage = null;
        MultipartFile picture = pictureDTO.getPicture();
        try {
            bufferedImage = ImageIO.read(picture.getInputStream());
        } catch (IOException e) {
            log.info("图片转化成bufferImage失败,可能不是图片文件");
            e.printStackTrace();
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "当前文件不是图片文件，无法转化图片存储");
        }
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        //设置参数
        pictureVO.setWidth(width);
        pictureVO.setHeight(height);
        //解析名称
        String picName = picture.getOriginalFilename();
        if (StringUtils.isEmpty(picName)) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片原名称为空");
        }
        int index = picName.lastIndexOf(".");
        if (index == -1) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片后缀缺失");
        }
        String suffix = picName.substring(index).toLowerCase();
        if (!suffix.matches(".(png|jpg|jpeg|gif|img)")) {
            log.info("图片解析后缀为:" + suffix);
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "图片后缀不规范,或非图片文件");
        }
        //图片重命名
        String picNewName = UUID.randomUUID().toString() + suffix;
        //生成多级路径
        String path = generatorPath(pictureDTO.getHead());
        File dir = new File(fileUploadServerLocalPath, path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //上传写入
        try {
            picture.transferTo(new File(dir, picNewName));
        } catch (IOException e) {
            log.debug("当前图片:" + picName + "上传存储失败", e);
        }
        String url = fileUploadResourceHost + path + picNewName;
        pictureVO.setUrl(url);
        return pictureVO;
    }

    public static String generatorPath(String prefix) {
        String baseStr = UUID.randomUUID().toString();//一般生成36个字符的字符串
        System.out.println(baseStr);
        StringBuffer pathBuilder = new StringBuffer();
        pathBuilder.append("/");
        pathBuilder.append(prefix);
        pathBuilder.append("/");
        for (int i = 0; i < 8; i++) {
            char charStr = 0;//这可能是一个空的字符符号
            if (i > (baseStr.length() - 1)) {
                //这时已经超过字符串长度,会抛异常outofbound
                pathBuilder.append("0/");
                continue;
            }
            charStr = baseStr.charAt(i);
            pathBuilder.append(charStr);
            pathBuilder.append("/");
        }
        return pathBuilder.toString();
    }


}
