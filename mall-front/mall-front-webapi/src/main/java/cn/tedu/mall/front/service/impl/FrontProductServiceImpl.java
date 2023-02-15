package cn.tedu.mall.front.service.impl;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.front.service.IFrontProductService;
import cn.tedu.mall.pojo.product.vo.*;
import cn.tedu.mall.product.service.front.IForFrontAttributeService;
import cn.tedu.mall.product.service.front.IForFrontSkuService;
import cn.tedu.mall.product.service.front.IForFrontSpuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FrontProductServiceImpl implements IFrontProductService {

    @DubboReference
    private IForFrontSpuService dubboSpuService;
    // 根据spuId查询sku集合的方法包含在下面的业务接口中
    @DubboReference
    private IForFrontSkuService dubboSkuService;
    // 根据spuId查询商品属性集合的业务逻辑层接口
    @DubboReference
    private IForFrontAttributeService dubboAttributeService;

    @Override
    public JsonPage<SpuListItemVO> listSpuByCategoryId(Long categoryId, Integer page, Integer pageSize) {
        // dubbo调用的方法是product模块编写的业务逻辑层方法
        // 这个方法实际上完成了分页操作,我们只需要调用即可
        JsonPage<SpuListItemVO> jsonPage=
                dubboSpuService.listSpuByCategoryId(categoryId,page,pageSize);
        // 别忘了返回jsonPage
        return jsonPage;
    }

    // 根据spuId查询spu信息
    @Override
    public SpuStandardVO getFrontSpuById(Long id) {
        SpuStandardVO spuStandardVO=dubboSpuService.getSpuById(id);
        return spuStandardVO;
    }

    // 根据spuId查询sku列表
    @Override
    public List<SkuStandardVO> getFrontSkusBySpuId(Long spuId) {
        List<SkuStandardVO> list=dubboSkuService.getSkusBySpuId(spuId);
        return list;
    }

    // 根据spuId查询spuDetail详情
    @Override
    public SpuDetailStandardVO getSpuDetail(Long spuId) {
        SpuDetailStandardVO spuDetailStandardVO=
                dubboSpuService.getSpuDetailById(spuId);
        return spuDetailStandardVO;
    }
    // 根据spuId查询当前商品的属性\规格列表
    @Override
    public List<AttributeStandardVO> getSpuAttributesBySpuId(Long spuId) {
        List<AttributeStandardVO> list=
                dubboAttributeService.getSpuAttributesBySpuId(spuId);
        return list;
    }
}
