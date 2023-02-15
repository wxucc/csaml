package cn.tedu.mall.pojo.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装微服务调用查询的分类数据
 * 成为分类树结构返回
 * @author xiaoxuwei
 * @version 1.0.0
 */
@ApiModel(value = "前台分类树")
@Data
public class FrontCategoryTreeVO<T> implements Serializable {

    @ApiModelProperty(value="分类列表,包含下级分类")
    private List<T> categories;




}
