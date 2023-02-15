package cn.tedu.mall.order.utils;

import java.util.UUID;

/**
 * 图片路径生成的工具类
 */
public class PathGenerator {

    public static String getUploadPath(String head) {
        String hash = UUID.randomUUID().toString();
        while (hash.length() < 8) {
            hash += "0";
        }
        String path = head;
        for (int i = 0; i < 8; i++) {
            path += "/" + hash.charAt(i);
        }
        return "/" + path + "/";
    }

}
