package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryAddNewResultVO implements Serializable {

    /**
     * 新增的类别的id
     */
    @ApiModelProperty(value = "新增的类别的id", position = 1)
    private Long id;

    public CategoryAddNewResultVO() {
    }

    public CategoryAddNewResultVO(Long id) {
        this.id = id;
    }

}
