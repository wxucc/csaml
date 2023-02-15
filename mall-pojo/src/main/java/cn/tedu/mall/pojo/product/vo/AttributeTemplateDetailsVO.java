package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AttributeTemplateDetailsVO implements Serializable {

    /**
     * 商品属性id
     */
    @ApiModelProperty(value = "属性模板id", position = 1)
    private Long id;

    /**
     * 属性模板名称
     */
    @ApiModelProperty(value = "属性模板名称", position = 2)
    private String name;

    /**
     * 属性模板名称的拼音
     */
    @ApiModelProperty(value = "属性模板名称的拼音", position = 3)
    private String pinyin;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "属性模板键词列表，各关键词使用英文的逗号分隔", position = 4)
    private String keywords;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "属性模板自定义排序序号", position = 5)
    private Integer sort;

    /**
     * 此模板下的属性的列表
     */
    @ApiModelProperty(value = "属性模板中的属性集合", position = 6)
    private List<AttributeListItemVO> attributes;

}
