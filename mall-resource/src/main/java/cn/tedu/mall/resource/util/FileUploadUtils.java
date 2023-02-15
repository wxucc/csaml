package cn.tedu.mall.resource.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FileUploadUtils {

    /**
     * 根据日期生成文件夹名称
     *
     * @return 文件夹名称，例如：2022/03/11
     */
    public static String generateDirNameByDate() {
        return DATE_FORMATTER.format(LocalDate.now());
    }

    /**
     * 根据当前日期与时间生成文件名
     *
     * @return 由年、月、日、时、分、秒、毫秒及6位随机数构成文件名，例如（实际无减号）：2022-03-11-09-56-35-978-123456
     */
    public static String generateFileNameByCurrentDateTime() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now()) + generateRandomNumber();
    }

    /**
     * 根据原始文件名获取其扩展名
     *
     * @param originalFileName 原始文件名
     * @return 原始文件名中的扩展名，如果无扩展名，则返回null
     */
    public static String getSuffixByOriginalFileName(String originalFileName) {
        // 如果传入的参数为null，则视为无扩展名
        if (originalFileName == null) {
            return null;
        }
        // 获取原始文件名中最后一个小数点的位置
        int lastDotPosition = originalFileName.lastIndexOf(".");
        // 如果原始文件名中无小数点，或小数点在第1位，视为无扩展名
        if (lastDotPosition == -1 || lastDotPosition == 0) {
            return null;
        }
        // 截取扩展名并返回
        return originalFileName.substring(lastDotPosition);
    }

    /**
     * 将以字符串表示的文件大小转换成以字节为单位的数值
     *
     * @param sizeLimit 以字符串表示的文件大小
     * @return 以字节为单位的数值
     */
    public static long getSizeLimitValue(String sizeLimit) {
        String regExpression = "(?i)^[1-9]\\d*(KB|MB|GB)?$";
        if (!sizeLimit.matches(regExpression)) {
            throw new RuntimeException("传入的文件大小限制值不是有效参数：" + sizeLimit);
        }
        if (sizeLimit.matches("^[1-9]\\d*$")) {
            return Long.parseLong(sizeLimit);
        } else {
            String valueString = sizeLimit.substring(0, sizeLimit.length() - 2);
            String unit = sizeLimit.substring(sizeLimit.length() - 2).toUpperCase();
            long value = Long.parseLong(valueString);
            switch (unit) {
                case "KB":
                    return value * 1024;
                case "MB":
                    return value * 1024 * 1024;
                case "GB":
                    return value * 1024 * 1024 * 1024;
                default:
                    throw new RuntimeException("传入的文件大小限制值的单位不可被识别：" + unit);
            }
        }
    }

    /**
     * 日期格式化模式字符串
     */
    private static final String PATTERN_DATE = "yyyy/MM/dd";
    /**
     * 日期时间格式化模式字符串
     */
    private static final String PATTERN_DATE_TIME = "yyyyMMddHHmmssSSS";
    /**
     * 格式化为日期的工具对象
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE);
    /**
     * 格式化为日期时间的工具对象
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
    /**
     * 随机数工具对象
     */
    private static final Random RANDOM = new Random();

    /**
     * 生成6位长度的随机数字
     *
     * @return 在[100000, 999999]区间的随机数字
     */
    private static int generateRandomNumber() {
        return RANDOM.nextInt(899999) + 100000;
    }

}
