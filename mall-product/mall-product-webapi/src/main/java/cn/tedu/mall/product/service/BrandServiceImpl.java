package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.BrandMapper;
import cn.tedu.mall.product.mapper.SpuMapper;
import cn.tedu.mall.pojo.product.dto.BrandAddNewDTO;
import cn.tedu.mall.pojo.product.dto.BrandUpdateDTO;
import cn.tedu.mall.pojo.product.model.Brand;
import cn.tedu.mall.pojo.product.vo.BrandStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>品牌业务实现类/p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void addNew(BrandAddNewDTO brandAddNewDTO) {
        // 检查品牌名称是否存在
        String brandName = brandAddNewDTO.getName();
        BrandStandardVO checkNameQueryResult = brandMapper.getByName(brandName);
        if (checkNameQueryResult != null) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "新增品牌失败，品牌名称(" + brandName + ")已存在！");
        }

        // 执行新增
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandAddNewDTO, brand);
        brand.setSort(brandAddNewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : brandAddNewDTO.getSort());
        log.debug("新增品牌:" + brand);
        int rows = brandMapper.insert(brand);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增品牌失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void deleteById(Long id) {
        // 检查尝试删除的品牌是否存在
        BrandStandardVO currentBrand = brandMapper.getById(id);
        if (currentBrand == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "删除品牌失败，尝试访问的数据不存在！");
        }

        // 查询关联spu
        int count = spuMapper.countByBrandId(id);
        if (count > 0) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "删除品牌失败，当前品牌存在关联的SPU数据！");
        }

        // 执行删除
        int rows = brandMapper.deleteById(id);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "删除品牌失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateFullInfoById(Long id, BrandUpdateDTO brandDTO) {
        // 检查尝试更新的品牌是否存在
        BrandStandardVO currentBrand = brandMapper.getById(id);
        if (currentBrand == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "更新品牌失败，尝试访问的数据不存在！");
        }

        // 检查新名称是否冲突
        BrandStandardVO checkNameQueryResult = brandMapper.getByName(brandDTO.getName());
        if (checkNameQueryResult != null && !checkNameQueryResult.getId().equals(id)) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "更新品牌基本信息失败，类别名称(" + checkNameQueryResult.getName() + ")已存在！");
        }

        // 执行更新
        Brand brand = new Brand();
        brand.setId(id);
        BeanUtils.copyProperties(brandDTO, brand);
        log.debug("更新品牌:" + brand);
        int rows = brandMapper.updateFullInfoById(brand);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "更新品牌失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public BrandStandardVO getById(Long id) {
        BrandStandardVO brandStandardVO = brandMapper.getById(id);
        if (brandStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "获取品牌详情失败，尝试访问的数据不存在！");
        }
        return brandStandardVO;
    }

    @Override
    public JsonPage<BrandStandardVO> list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        log.debug("查询品牌分页：page={}, size={}", page, pageSize);
        List<BrandStandardVO> brands = brandMapper.list();
        return JsonPage.restPage(new PageInfo<>(brands));
    }

    @Override
    public JsonPage<BrandStandardVO> listByCategoryId(Long categoryId, Integer page, Integer pageSize) {
        log.debug("查询商品分类关联的商品品牌，商品分类id为" + categoryId);
        PageHelper.startPage(page, pageSize);
        List<BrandStandardVO> brands = brandMapper.listByCategoryId(categoryId);
        return JsonPage.restPage(new PageInfo<>(brands));
    }

}
