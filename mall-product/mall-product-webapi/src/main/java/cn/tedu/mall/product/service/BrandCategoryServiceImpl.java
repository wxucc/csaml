package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.mapper.BrandCategoryMapper;
import cn.tedu.mall.pojo.product.model.BrandCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>品牌类别关联数据的业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class BrandCategoryServiceImpl implements IBrandCategoryService {

    @Autowired
    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public void bindBrandAndCategory(Long brandId, Long categoryId) {
        // 检查绑定关系是否存在
        int count = brandCategoryMapper.countByBrandIdAndCategoryId(brandId, categoryId);
        if (count > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "绑定品牌与类别失败，绑定关系已经存在！");
        }

        // 执行绑定
        BrandCategory brandCategory = new BrandCategory();
        brandCategory.setBrandId(brandId);
        brandCategory.setCategoryId(categoryId);
        int rows = brandCategoryMapper.insert(brandCategory);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "绑定品牌与类别失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void unbindBrandAndCategory(Long brandId, Long categoryId) {
        // 检查绑定关系是否存在
        BrandCategory brandCategory = new BrandCategory();
        brandCategory.setBrandId(brandId);
        brandCategory.setCategoryId(categoryId);
        int count = brandCategoryMapper.countByBrandIdAndCategoryId(brandId, categoryId);
        if (count != 1) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "解绑品牌与类别失败，绑定关系不存在！");
        }

        // 执行解绑
        int rows = brandCategoryMapper.deleteByBrandIdAndCategoryId(brandId, categoryId);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "解绑品牌与类别失败，服务器忙，请稍后再次尝试！");
        }
    }

}
