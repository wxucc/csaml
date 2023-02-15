package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SpuListItemVO implements Serializable {

    /**
     * 数据id
     */
    @ApiModelProperty(value = "SPU id", position = 1)
    private Long id;

    /**
     * SPU名称
     */
    @ApiModelProperty(value = "SPU名称", position = 2)
    private String name;

    /**
     * SPU编号
     */
    @ApiModelProperty(value = "SPU编号", position = 3)
    private String typeNumber;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", position = 4)
    private String title;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", position = 5)
    private String description;

    /**
     * 价格（显示在列表中）
     */
    @ApiModelProperty(value = "价格", position = 6)
    private BigDecimal listPrice;

    /**
     * 当前库存（冗余）
     */
    @ApiModelProperty(value = "当前库存", position = 7)
    private Integer stock;

    /**
     * 库存预警阈值（冗余）
     */
    @ApiModelProperty(value = "库存预警阈值", position = 8)
    private Integer stockThreshold;

    /**
     * 计件单位
     */
    @ApiModelProperty(value = "计件单位", position = 9)
    private String unit;

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id", position = 14)
    private Long albumId;

    /**
     * 组图URLs，使⽤JSON格式表示
     */
    @ApiModelProperty(value = "组图URL", position = 15)
    private String pictures;

    /**
     * 销量（冗余）
     */
    @ApiModelProperty(value = "销量", position = 18)
    private Integer sales;

    /**
     * 是否标记为删除，1=已删除，0=未删除
     */
    @ApiModelProperty(value = "是否标记为删除，1=已删除，0=未删除", position = 21)
    private Integer deleted;

    /**
     * 是否上架（发布），1=已上架，0=未上架 （下架）
     */
    @ApiModelProperty(value = "是否上架（发布），1=已上架，0=未上架 （下架）", position = 22)
    private Integer published;

    /**
     * 是否新品，1=新品，0=非新品
     */
    @ApiModelProperty(value = "是否新品，1=新品，0=非新品", position = 23)
    private Integer newArrival;

    /**
     * 是否推荐，1=推荐，0=不推荐
     */
    @ApiModelProperty(value = "是否推荐，1=推荐，0=不推荐", position = 24)
    private Integer recommend;

    /**
     * 是否已审核，1=已审核，0=未审核
     */
    @ApiModelProperty(value = "是否已审核，1=已审核，0=未审核", position = 25)
    private Integer checked;

    /**
     * 审核⼈（冗余）
     */
    @ApiModelProperty(value = "审核⼈", position = 26)
    private String checkUser;

    /**
     * 审核通过时间（冗余）
     */
    @ApiModelProperty(value = "审核通过时间", position = 27)
    private LocalDateTime gmtCheck;

    /**
     * ⾃定义排序序号
     */
    @ApiModelProperty(value = "⾃定义排序序号", position = 28)
    private Integer sort;

}
