package cn.tedu.mall.product.service;

import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.product.service.front.IForFrontSkuService;
import cn.tedu.mall.product.mapper.SkuMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Service
public class ForFrontSkuServiceImpl implements IForFrontSkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public List<SkuStandardVO> getSkusBySpuId(Long spuId) {
        List<SkuStandardVO> skus=skuMapper.listBySpuId(spuId);
        return skus;
    }
}
