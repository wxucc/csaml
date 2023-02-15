package cn.tedu.mall.product.service;

import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import cn.tedu.mall.product.service.front.IForFrontCategoryService;
import cn.tedu.mall.product.mapper.CategoryMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Service
public class ForFrontCategoryServiceImpl implements IForFrontCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryStandardVO> getCategoryList() {
        return categoryMapper.selectAllCategories();
    }
}
