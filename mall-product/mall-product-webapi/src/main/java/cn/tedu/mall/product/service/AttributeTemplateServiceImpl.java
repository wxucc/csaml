package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.AttributeMapper;
import cn.tedu.mall.product.mapper.AttributeTemplateMapper;
import cn.tedu.mall.product.mapper.CategoryAttributeTemplateMapper;
import cn.tedu.mall.product.mapper.CategoryMapper;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateUpdateDTO;
import cn.tedu.mall.pojo.product.model.AttributeTemplate;
import cn.tedu.mall.pojo.product.model.CategoryAttributeTemplate;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateListItemVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateStandardVO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>属性模板业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class AttributeTemplateServiceImpl implements IAttributeTemplateService {

    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private AttributeTemplateMapper attributeTemplateMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;

    @Override
    public void addNew(AttributeTemplateAddNewDTO attributeTemplateAddnewDTO) {
        // 检查类别是否有效
        Long categoryId = attributeTemplateAddnewDTO.getCategoryId();
        CategoryStandardVO category = categoryMapper.getById(categoryId);
        if (category == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "新增属性模板失败，选择的【类别】不存在！");
        }
        if (category.getParent() == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增属性模板失败，选择的【类别】数据不完整，请联系系统管理员！");
        }
        if (!category.getParent().equals(1)) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增属性模板失败，选择的【类别】仍包括子级！");
        }

        // 检查名称是否被占用
        String attributeTemplateName = attributeTemplateAddnewDTO.getName();
        Object checkNameQueryResult = attributeTemplateMapper.getByName(attributeTemplateName);
        if (checkNameQueryResult != null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增属性模板失败，属性模板名称(" + attributeTemplateName + ")已存在！");
        }

        // 执行新增属性模板
        AttributeTemplate attributeTemplate = new AttributeTemplate();
        BeanUtils.copyProperties(attributeTemplateAddnewDTO, attributeTemplate);
        attributeTemplate.setSort(attributeTemplateAddnewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : attributeTemplateAddnewDTO.getSort());
        log.debug("新增属性模板:" + attributeTemplate);
        int rows = attributeTemplateMapper.insert(attributeTemplate);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增属性模板失败，服务器忙，请稍后再次尝试！");
        }

        // 执行新增类别与属性模板的关联
        CategoryAttributeTemplate categoryAttributeTemplate = new CategoryAttributeTemplate();
        categoryAttributeTemplate.setCategoryId(attributeTemplateAddnewDTO.getCategoryId());
        categoryAttributeTemplate.setAttributeTemplateId(attributeTemplate.getId());
        rows = categoryAttributeTemplateMapper.insert(categoryAttributeTemplate);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增属性模板失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void deleteById(Long id) {
        // 检查尝试删除的属性模板是否存在
        Object currentData = attributeTemplateMapper.getById(id);
        if (currentData == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "删除属性模板失败，尝试访问的数据不存在！");
        }

        // 检查是否被关联到商品属性
        int attributeCount = attributeMapper.countByTemplateId(id);
        if (attributeCount > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除属性模板失败，仍有商品属性关联到此模板，请先删除相关商品属性或解除关联后再次尝试！");
        }

        // 执行删除
        log.debug("根据属性模板id:" + id + "删除模板");
        int rows = attributeTemplateMapper.deleteById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除属性模板失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateById(Long id, AttributeTemplateUpdateDTO attributeTemplateUpdateDTO) {
        // 检查尝试更新的属性模板是否存在
        Object currentData = attributeTemplateMapper.getById(id);
        if (currentData == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新属性模板失败，尝试访问的数据不存在！");
        }

        // 检查新名称是否冲突
        String attributeTemplateNewName = attributeTemplateUpdateDTO.getName();
        AttributeTemplateStandardVO checkNameQueryResult = attributeTemplateMapper.getByName(attributeTemplateNewName);
        if (checkNameQueryResult != null && !checkNameQueryResult.getId().equals(id)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "更新属性模板信息失败，属性模板名称(" + attributeTemplateNewName + ")已存在！");
        }

        // 执行更新
        AttributeTemplate attributeTemplate = new AttributeTemplate();
        attributeTemplate.setId(id);
        BeanUtils.copyProperties(attributeTemplateUpdateDTO, attributeTemplate);
        log.debug("更新属性模板" + attributeTemplate);
        int rows = attributeTemplateMapper.update(attributeTemplate);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新属性模板失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public AttributeTemplateDetailsVO getDetailsById(Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateMapper.getDetailsById(id);
        if (attributeTemplateDetailsVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取属性模板失败，尝试访问的数据不存在！");
        }
        return attributeTemplateDetailsVO;
    }

    @Override
    public AttributeTemplateDetailsVO getDetailsIncludeSaleAttributeById(Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateMapper.getDetailsByIdAndAttributeType(id, 1);
        if (attributeTemplateDetailsVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取属性模板失败，尝试访问的数据不存在！");
        }
        return attributeTemplateDetailsVO;
    }

    @Override
    public AttributeTemplateDetailsVO getDetailsIncludeNonSaleAttributeById(Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateMapper.getDetailsByIdAndAttributeType(id, 0);
        if (attributeTemplateDetailsVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取属性模板失败，尝试访问的数据不存在！");
        }
        return attributeTemplateDetailsVO;
    }

    @Override
    public JsonPage<AttributeTemplateStandardVO> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        log.debug("查询属性模板分页数据:page=" + pageNum + ";size=" + pageSize);
        List<AttributeTemplateStandardVO> attributeTemplateSimpleVOList = attributeTemplateMapper.list();
        return JsonPage.restPage(new PageInfo<>(attributeTemplateSimpleVOList));
    }

    @Override
    public List<AttributeTemplateListItemVO> listByCategoryId(Long categoryId) {
        return attributeTemplateMapper.listByCategoryId(categoryId);
    }

}
