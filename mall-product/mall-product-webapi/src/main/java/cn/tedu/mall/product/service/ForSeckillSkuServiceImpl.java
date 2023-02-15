package cn.tedu.mall.product.service;

import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.product.mapper.SkuMapper;
import cn.tedu.mall.product.service.seckill.IForSeckillSkuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class ForSeckillSkuServiceImpl implements IForSeckillSkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public SkuStandardVO getById(Long skuId) {
        return skuMapper.getById(skuId);
    }
}
