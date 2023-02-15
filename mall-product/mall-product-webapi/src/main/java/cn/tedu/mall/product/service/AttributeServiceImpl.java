package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.dto.AttributeAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeUpdateDTO;
import cn.tedu.mall.pojo.product.model.Attribute;
import cn.tedu.mall.pojo.product.vo.AttributeDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.AttributeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>商品属性业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class AttributeServiceImpl implements IAttributeService {

    @Autowired
    private AttributeMapper attributeMapper;


    @Override
    public void addNew(AttributeAddNewDTO attributeAddnewDTO) {
        log.debug("增加商品属性：{}", attributeAddnewDTO);
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(attributeAddnewDTO, attribute);
        attribute.setSort(attributeAddnewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : attributeAddnewDTO.getSort());
        int rows = attributeMapper.insert(attribute);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增商品属性失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("删除商品属性，id={}", id);
        // 检查尝试删除的数据是否存在
        Object currentData = attributeMapper.getById(id);
        if (currentData == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "删除商品属性失败，尝试访问的数据不存在！");
        }

        // 执行删除
        log.debug("删除id为{}的商品属性数据", id);
        int rows = attributeMapper.deleteById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除商品属性失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateById(Long id, AttributeUpdateDTO attributeUpdateDTO) {
        // 检查尝试更新的数据是否存在
        Object currentData = attributeMapper.getById(id);
        if (currentData == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新商品属性失败，尝试访问的数据不存在！");
        }

        // 执行更新
        Attribute attribute = new Attribute();
        log.debug("更新属性数据:" + attribute);
        BeanUtils.copyProperties(attributeUpdateDTO, attribute);
        attribute.setId(id);
        int rows = attributeMapper.update(attribute);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新商品属性失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public AttributeDetailsVO getDetailsById(Long id) {
        AttributeDetailsVO attributeDetailsVO = attributeMapper.getDetailsById(id);
        if (attributeDetailsVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取商品属性详情（id=" + id + "）失败，尝试访问的数据不存在！");
        }
        return attributeDetailsVO;
    }

    @Override
    public JsonPage<AttributeStandardVO> listByTemplateId(Long templateId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttributeStandardVO> attributes = attributeMapper.listByTemplateId(templateId);
        return JsonPage.restPage(new PageInfo<>(attributes));
    }

    @Override
    public JsonPage<AttributeStandardVO> listSaleAttributesByTemplateId(Long templateId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttributeStandardVO> attributes = attributeMapper.listByTemplateIdAndType(templateId, 1);
        return JsonPage.restPage(new PageInfo<>(attributes));
    }

    @Override
    public JsonPage<AttributeStandardVO> listNonSaleAttributesByTemplateId(Long templateId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttributeStandardVO> attributes = attributeMapper.listByTemplateIdAndType(templateId, 0);
        return JsonPage.restPage(new PageInfo<>(attributes));
    }

}
