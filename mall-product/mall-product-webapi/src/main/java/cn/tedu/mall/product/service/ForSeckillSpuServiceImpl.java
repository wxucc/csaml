package cn.tedu.mall.product.service;

import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.product.mapper.SpuDetailMapper;
import cn.tedu.mall.product.mapper.SpuMapper;
import cn.tedu.mall.product.service.seckill.IForSeckillSpuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
@Service
public class ForSeckillSpuServiceImpl implements IForSeckillSpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Override
    public SpuStandardVO getSpuById(Long spuId) {
        return spuMapper.getById(spuId);
    }

    @Override
    public SpuDetailStandardVO getSpuDetailById(Long spuId) {
        return spuDetailMapper.getBySpuId(spuId);
    }
}
