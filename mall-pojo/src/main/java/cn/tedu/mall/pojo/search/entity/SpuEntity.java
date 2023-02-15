package cn.tedu.mall.pojo.search.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(indexName = "cool_shark_mall_index")
@ApiModel(value="对应搜索的文档entity")
public class SpuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @Id
    @ApiModelProperty(value="spu id")
    private Long id;

    /**
     * SPU名称
     */
    @Field(name = "name")
    @ApiModelProperty(value="SPU名称")
    private String name;

    /**
     * SPU编号
     */
    @Field(name = "type_number")
    @ApiModelProperty(value="SPU编号")
    private String typeNumber;

    /**
     * 标题
     */
    @Field(name="title")
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 简介
     */
    @Field(name="description")
    @ApiModelProperty(value="简介")
    private String description;

    /**
     * 价格（显示在列表中）
     */
    @Field(name="list_price")
    @ApiModelProperty(value="价格（显示在列表中）")
    private BigDecimal listPrice;

    /**
     * 当前库存（冗余）
     */
    @Field(name="stock")
    @ApiModelProperty(value="当前库存（冗余）")
    private Integer stock;

    /**
     * 库存预警阈值（冗余）
     */
    @Field(name="stock_threshold")
    @ApiModelProperty(value="库存预警阈值（冗余）")
    private Integer stockThreshold;

    /**
     * 计件单位
     */
    @Field(name="unit")
    @ApiModelProperty(value="计件单位d")
    private String unit;

    /**
     * 品牌id
     */
    @Field(name="brand_id")
    @ApiModelProperty(value="品牌id")
    private Long brandId;

    /**
     * 品牌名称（冗余）
     */
    @Field(name="brand_name")
    @ApiModelProperty(value="品牌名称（冗余）")
    private String brandName;

    /**
     * 类别id
     */
    @Field(name="category_id")
    @ApiModelProperty(value="类别id")
    private Long categoryId;

    /**
     * 类别名称（冗余）
     */
    @Field(name="category_name")
    @ApiModelProperty(value="类别名称（冗余）")
    private String categoryName;

    /**
     * 相册id
     */
    @Field(name="album_id")
    @ApiModelProperty(value="相册id")
    private Long albumId;

    /**
     * 组图URLs，使⽤JSON格式表示
     */
    @Field(name="pictures")
    @ApiModelProperty(value="组图URLs，使⽤JSON格式表示")
    private String pictures;

    /**
     * 关键词列表，各关键词使⽤英⽂的逗号分隔
     */
    @Field(name="keywords")
    @ApiModelProperty(value="关键词列表，各关键词使⽤英⽂的逗号分隔")
    private String keywords;

    /**
     * 标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个
     */
    @Field(name="tags")
    @ApiModelProperty(value="标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个")
    private String tags;

    /**
     * 销量（冗余）
     */
    @Field(name="sales")
    @ApiModelProperty(value="销量（冗余）")
    private Integer sales;

    /**
     * 买家评论数量总和（冗余）
     */
    @Field(name="comment_count")
    @ApiModelProperty(value="买家评论数量总和（冗余）")
    private Integer commentCount;

    /**
     * 买家好评数量总和（冗余）
     */
    @Field(name="positive_comment_count")
    @ApiModelProperty(value="买家好评数量总和（冗余）")
    private Integer positiveCommentCount;

    /**
     * ⾃定义排序序号
     */
    @Field(name="sort")
    @ApiModelProperty(value="⾃定义排序序号")
    private Integer sort;

    /**
     * 是否标记为删除，1=已删除，0=未删除
     */
    @Field(name="is_delete")
    @ApiModelProperty(value="是否标记为删除，1=已删除，0=未删除")
    private Integer isDelete;

    /**
     * 是否上架（发布），1=已上架，0=未上架 （下架）
     */
    @Field(name="is_publish")
    @ApiModelProperty(value="是否上架（发布），1=已上架，0=未上架 （下架）")
    private Integer isPublish;

    /**
     * 是否新品，1=新品，0=非新品
     */
    @Field(name="is_new")
    @ApiModelProperty(value="是否新品，1=新品，0=非新品")
    private Integer isNew;

    /**
     * 是否推荐，1=推荐，0=不推荐
     */
    @Field(name="is_recommend")
    @ApiModelProperty(value="是否推荐，1=推荐，0=不推荐")
    private Integer isRecommend;

    /**
     * 是否已审核，1=已审核，0=未审核
     */
    @Field(name="is_checked")
    @ApiModelProperty(value="是否已审核，1=已审核，0=未审核")
    private Integer isChecked;

    /**
     * 审核⼈（冗余）
     */
    @Field(name="check_user")
    @ApiModelProperty(value="审核⼈（冗余）")
    private String checkUser;

    /**
     * 审核通过时间（冗余）
     */
    @Field(name="gmt_check")
    @ApiModelProperty(value="审核通过时间（冗余）")
    private LocalDateTime gmtCheck;

    /**
     * 数据创建时间
     */
    @Field(name="gmt_create")
    @ApiModelProperty(value="数据创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @Field(name="gmt_modified")
    @ApiModelProperty(value="数据最后修改时间")
    private LocalDateTime gmtModified;
}
