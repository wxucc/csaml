package cn.tedu.mall.pojo.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 前台商城商品对象
 * @author xiaoxuwei
 * @version 1.0.0
 */
@ApiModel(value="商品详情",description = "包含了展示商品详情的所有数据")
@Data
@Deprecated
public class FrontProductVO implements Serializable {
    @ApiModelProperty(value = "spu的id")
    private Long id;
    @ApiModelProperty(value = "SPU名称")
    private String name;
    @ApiModelProperty(value = "SPU编号")
    private String typeNumber;
    @ApiModelProperty(value = "SPU标题")
    private String title;
    @ApiModelProperty(value = "SPU简介")
    private String description;
    @ApiModelProperty(value = "SPU价格（显示在列表中）")
    private BigDecimal listPrice;
    @ApiModelProperty(value = "SPU当前库存（冗余）")
    private Integer stock;
    @ApiModelProperty(value = "SPU库存预警阈值（冗余）")
    private Integer stockThreshold;
    @ApiModelProperty(value = "SPU计件单位")
    private String unit;
    @ApiModelProperty(value = "SPU品牌id")
    private Long brandId;
    @ApiModelProperty(value = "SPU品牌名称（冗余）")
    private String brandName;
    @ApiModelProperty(value = "SPU类别id")
    private Long categoryId;
    @ApiModelProperty(value = "SPU类别名称（冗余）")
    private String categoryName;
    @ApiModelProperty(value = "SPU相册id")
    private Long albumId;
    @ApiModelProperty(value = "SPU组图URLs，使⽤JSON格式表示")
    private String pictures;
    @ApiModelProperty(value = "SPU关键词列表，各关键词使⽤英⽂的逗号分隔")
    private String keywords;
    @ApiModelProperty(value = "SPU标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个")
    private String tags;
    @ApiModelProperty(value = "SPU销量（冗余）")
    private Integer sales;
    @ApiModelProperty(value = "SPU买家评论数量总和（冗余）")
    private Integer commentCount;
    @ApiModelProperty(value = "SPU买家好评数量总和（冗余）")
    private Integer positiveCommentCount;
    @ApiModelProperty(value = "SPU⾃定义排序序号")
    private Integer sort;
    @ApiModelProperty(value = "SPU是否标记为删除，1=已删除，0=未删除")
    private Integer isDelete;
    @ApiModelProperty(value = "SPU是否上架（发布），1=已上架，0=未上架 （下架）")
    private Integer isPublish;
    @ApiModelProperty(value = "SPU是否新品，1=新品，0=非新品")
    private Integer isNew;
    @ApiModelProperty(value = "SPU是否推荐，1=推荐，0=不推荐")
    private Integer isRecommend;
    @ApiModelProperty(value = "SPU是否已审核，1=已审核，0=未审核")
    private Integer isChecked;
    @ApiModelProperty(value = "SPU审核⼈（冗余）")
    private String checkUser;
    @ApiModelProperty(value = "SPU审核通过时间（冗余）")
    private LocalDateTime gmtCheck;
    @ApiModelProperty(value = "spu包含了sku列表")
    private List<FrontSkuSimpleVO> skusList;
    @ApiModelProperty(value="spu关联的属性列表")
    private List<FrontAttributeSimpleVO> frontAttributeVOList;

}
