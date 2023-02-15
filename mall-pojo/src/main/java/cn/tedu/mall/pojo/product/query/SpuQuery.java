package cn.tedu.mall.pojo.product.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpuQuery implements Serializable {

    /**
     * SPU id
     */
    @ApiModelProperty(value = "SPU id")
    private Long id;

    /**
     * SPU名称
     */
    @ApiModelProperty(value = "SPU名称")
    private String name;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 是否上架（发布）
     */
    @ApiModelProperty(value = "是否上架（发布），1=已上架，0=未上架（下架）")
    private Integer published;

    /**
     * 是否已审核
     */
    @ApiModelProperty(value = "是否已审核，1=已审核，0=未审核")
    private Integer checked;

    /**
     * SPU编号
     */
    @ApiModelProperty(value = "SPU编号")
    private String typeNumber;

    /**
     * 分类Id
     */
    @ApiModelProperty(value = "分类Id")
    private Long categoryId;

    /**
     * 是否推荐
     */
    @ApiModelProperty(value = "是否推荐，1=推荐，0=不推荐")
    private Integer recommend;

    /**
     * 是否新品
     */
    @ApiModelProperty(value = "是否新品，1=新品，0=非新品")
    private Integer newArrival;

    /**
     * 是否标记为删除
     */
    @ApiModelProperty(value = "是否标记为删除，1=已删除，0=未删除")
    private Integer deleted;

    /**
     * SPU的详情
     */
    @ApiModelProperty(value = "SPU的详情")
    private String detail;

    /**
     * 排序规则
     */
    @ApiModelProperty(value = "排序规则（暂用，必须按照SQL语法填写）")
    private String orderBy;

}
