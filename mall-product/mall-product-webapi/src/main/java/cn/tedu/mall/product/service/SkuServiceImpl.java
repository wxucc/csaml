package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.SkuMapper;
import cn.tedu.mall.product.mapper.SkuSpecificationMapper;
import cn.tedu.mall.pojo.product.dto.SkuAddNewDTO;
import cn.tedu.mall.pojo.product.dto.SkuUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.model.Sku;
import cn.tedu.mall.pojo.product.model.SkuSpecification;
import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.product.utils.IdGeneratorUtils;
import cn.tedu.mall.product.utils.ListConvertUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>SKU（Stock Keeping Unit）业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class SkuServiceImpl implements ISkuService {

    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SkuSpecificationMapper skuSpecificationMapper;

    @Override
    public void addNew(SkuAddNewDTO skuAddNewDTO) {
        log.debug("skuAddNewDTO = {}", skuAddNewDTO);
        Long skuId = IdGeneratorUtils.getDistributeId(IdGeneratorUtils.Key.SKU);

        Sku sku = new Sku();
        BeanUtils.copyProperties(skuAddNewDTO, sku);
        sku.setId(skuId);
        sku.setSort(skuAddNewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : skuAddNewDTO.getSort());
        skuMapper.insert(sku);

        String specifications = skuAddNewDTO.getSpecifications();
        log.debug("specifications = {}", specifications);
        List<SkuSpecification> skuSpecificationList = ListConvertUtils.stringToList(specifications, SkuSpecification.class);
        if (skuSpecificationList != null) {
            for (SkuSpecification skuSpecification : skuSpecificationList) {
                log.debug("skuSpecification = {}", skuSpecification);
                skuSpecification.setSkuId(skuId);
            }
        }
        skuSpecificationMapper.insertBatch(skuSpecificationList);
    }

    @Override
    public void  updateFullInfoById(Long id, SkuUpdateFullInfoDTO skuUpdateFullInfoDTO) {
        Object checkExistQueryResult = skuMapper.getById(id);
        if (checkExistQueryResult == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新SKU失败，尝试访问的数据不存在！");
        }

        Sku sku = new Sku();
        sku.setId(id);
        BeanUtils.copyProperties(skuUpdateFullInfoDTO, sku);
        int rows = skuMapper.updateFullInfoById(sku);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新SKU失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public SkuStandardVO getById(Long id) {
        SkuStandardVO sku = skuMapper.getById(id);
        if (sku == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取SKU详情失败，尝试访问的数据不存在！");
        }
        return sku;
    }

    @Override
    public JsonPage<SkuStandardVO> list(Long spuId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SkuStandardVO> skus = skuMapper.listBySpuId(spuId);
        return JsonPage.restPage(new PageInfo<>(skus));
    }

}
