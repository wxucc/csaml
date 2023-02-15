package cn.tedu.mall.resource.util;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static ImageInfo getImageInfo(String filePath) throws IOException {
        return getImageInfo(new File(filePath));
    }

    public static ImageInfo getImageInfo(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setWidth(bufferedImage.getWidth());
        imageInfo.setHeight(bufferedImage.getHeight());
        return imageInfo;
    }

    @Data
    public static class ImageInfo {

        private Integer width;
        private Integer height;

    }

}
