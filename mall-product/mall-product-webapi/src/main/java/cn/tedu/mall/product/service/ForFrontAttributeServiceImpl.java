package cn.tedu.mall.product.service;

import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import cn.tedu.mall.product.service.front.IForFrontAttributeService;
import cn.tedu.mall.product.mapper.AttributeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Service
public class ForFrontAttributeServiceImpl implements IForFrontAttributeService {
    @Autowired
    private AttributeMapper attributeMapper;
    @Override
    public List<AttributeStandardVO> getSpuAttributesBySpuId(Long spuId) {
        return attributeMapper.selectAttributesBySpuId(spuId);
    }
}
