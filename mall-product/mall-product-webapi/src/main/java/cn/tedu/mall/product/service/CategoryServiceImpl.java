package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.CategoryAttributeTemplateMapper;
import cn.tedu.mall.product.mapper.CategoryMapper;
import cn.tedu.mall.pojo.product.dto.CategoryAddNewDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateBaseInfoDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.model.Category;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>类别业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;

    @Override
    public Long addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // 检查名称是否被占用
        Object checkNameQueryResult = categoryMapper.getByName(categoryAddNewDTO.getName());
        if (checkNameQueryResult != null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增类别失败，类别名称(" + categoryAddNewDTO.getName() + ")已存在！");
        }

        // 如果未确定父级类别，则视为一级类别
        if (categoryAddNewDTO.getParentId() == null) {
            categoryAddNewDTO.setParentId(0L);
        }

        // 检查父级是否存在
        CategoryStandardVO parentCategory = categoryMapper.getById(categoryAddNewDTO.getParentId());
        if (categoryAddNewDTO.getParentId() != 0 && parentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "新增类别失败，父级类别不存在！");
        }

        // 复制属性到即将插入数据的对象中
        // 由于存在处理业务的可能，不推荐使用BeanUtils.copyProperties()方法
        Category category = new Category();
        category.setParentId(categoryAddNewDTO.getParentId());
        category.setName(categoryAddNewDTO.getName());
        category.setKeywords(categoryAddNewDTO.getKeywords());
        category.setIcon(categoryAddNewDTO.getIcon());
        category.setEnable(categoryAddNewDTO.getEnable());
        category.setDisplay(categoryAddNewDTO.getDisplay());
        category.setSort(categoryAddNewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : categoryAddNewDTO.getSort());

        // 补全depth属性，即类别的深度
        int depth = 1;
        if (categoryAddNewDTO.getParentId() != 0) {
            depth = parentCategory.getDepth() + 1;
        }
        category.setDepth(depth);

        // 补全属性
        category.setParent(0); // 新增的类别默认不是父级

        // 执行插入数据
        int rows = categoryMapper.insert(category);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增类别失败，服务器忙，请稍后再次尝试！");
        }

        // 新增类别后，如果当前新增的类别不是1级类别，且父级类别的parent为0，则需更新为1
        if (categoryAddNewDTO.getParentId() != 0 && parentCategory.getParent() == 0) {
            rows = categoryMapper.updateParentById(parentCategory.getId(), 1);
            if (rows != 1) {
                throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增类别失败，服务器忙，请稍后再次尝试！");
            }
        }

        // 返回当前类别的id
        return category.getId();
    }

    @Override
    public void deleteById(Long id) {
        // 检查尝试删除的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "删除类别失败，尝试访问的数据不存在！");
        }

        // 检查尝试删除的类别是否存在子级类别
        int childCount = categoryMapper.countByParentId(id);
        if (childCount > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除类别失败，该类别下仍有子级类别！");
        }

        // 检查尝试删除的类别是否关联了属性模板
        int relationCount = categoryAttributeTemplateMapper.countByCategoryId(id);
        if (relationCount > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除类别失败，此类别仍关联了属性模板！");
        }

        // 执行删除
        int rows = categoryMapper.deleteById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除类别失败，服务器忙，请稍后再次尝试！");
        }

        // 如果父级类别已经没有其它子级类别，则父级类型的is_parent更新为0
        if (currentCategory.getParentId() != 0) {
            CategoryStandardVO parentCategory = categoryMapper.getById(currentCategory.getParentId());
            if (parentCategory != null) {
                List<?> currentCategorySiblings = categoryMapper.listByParentId(parentCategory.getId());
                if (currentCategorySiblings.size() == 0) {
                    rows = categoryMapper.updateParentById(parentCategory.getId(), 0);
                    if (rows != 1) {
                        throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除类别失败，服务器忙，请稍后再次尝试！");
                    }
                }
            }
        }
    }

    @Override
    public void setEnableById(Long id) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "启用类别失败，尝试访问的数据不存在！");
        }

        // 检查当前状态值
        if (currentCategory.getEnable().equals(1)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "启用类别失败，类别当前已经启用！");
        }

        // 执行更新
        int rows = categoryMapper.updateEnableById(id, 1);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "启用类别失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void setDisableById(Long id) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "禁用类别失败，尝试访问的数据不存在！");
        }

        // 检查当前状态值
        if (currentCategory.getEnable().equals(0)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "禁用类别失败，类别当前已经启用！");
        }

        // 执行更新
        int rows = categoryMapper.updateEnableById(id, 0);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "禁用类别失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void setDisplayById(Long id) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "显示类别失败，尝试访问的数据不存在！");
        }

        // 检查当前状态值
        if (currentCategory.getDisplay().equals(1)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "显示类别失败，类别当前已经启用！");
        }

        // 执行更新
        int rows = categoryMapper.updateDisplayById(id, 1);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "显示类别失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void setHiddenById(Long id) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "隐藏类别失败，尝试访问的数据不存在！");
        }

        // 检查当前状态值
        if (currentCategory.getDisplay().equals(0)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "隐藏类别失败，类别当前已经启用！");
        }

        // 执行更新
        int rows = categoryMapper.updateDisplayById(id, 0);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "隐藏类别失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateBaseInfoById(Long id, CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新类别基本信息失败，尝试访问的数据不存在！");
        }

        // 检查新名称是否冲突
        CategoryStandardVO checkNameQueryResult = categoryMapper.getByName(categoryUpdateBaseInfoDTO.getName());
        if (checkNameQueryResult != null && !checkNameQueryResult.getId().equals(id)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "更新类别基本信息失败，类别名称(" + categoryUpdateBaseInfoDTO.getName() + ")已存在！");
        }

        // 执行更新
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateBaseInfoDTO, category);
        category.setId(id);
        int rows = categoryMapper.updateBaseInfoById(category);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新类别基本信息失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateFullInfoById(Long id, CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO) {
        // 检查尝试编辑的类别是否存在
        CategoryStandardVO currentCategory = categoryMapper.getById(id);
        if (currentCategory == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新类别信息失败，尝试访问的数据不存在！");
        }

        // 检查新名称是否冲突
        CategoryStandardVO checkNameQueryResult = categoryMapper.getByName(categoryUpdateFullInfoDTO.getName());
        if (checkNameQueryResult != null && !checkNameQueryResult.getId().equals(id)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "更新类别信息失败，类别名称(" + categoryUpdateFullInfoDTO.getName() + ")已存在！");
        }

        // 执行更新
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateFullInfoDTO, category);
        category.setId(id);
        int rows = categoryMapper.updateBaseInfoById(category);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新类别信息失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public CategoryStandardVO getById(Long id) {
        CategoryStandardVO categoryStandardVO = categoryMapper.getById(id);
        if (categoryStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取类别详情失败，尝试访问的数据不存在！");
        }
        return categoryStandardVO;
    }

    @Override
    public JsonPage<CategoryStandardVO> listByBrand(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryStandardVO> categories = categoryMapper.listByBrandId(brandId);
        return JsonPage.restPage(new PageInfo<>(categories));
    }

    @Override
    public JsonPage<CategoryStandardVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryStandardVO> categories = categoryMapper.listByParentId(parentId);
        return JsonPage.restPage(new PageInfo<>(categories));
    }

}
