package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.dto.SpuAddNewDTO;
import cn.tedu.mall.pojo.product.dto.SpuUpdateDTO;
import cn.tedu.mall.pojo.product.model.Spu;
import cn.tedu.mall.pojo.product.model.SpuDetail;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.pojo.product.vo.BrandStandardVO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.product.constant.DataCommonConst;
import cn.tedu.mall.product.mapper.*;
import cn.tedu.mall.product.utils.IdGeneratorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>SPU（Standard Product Unit）业务实现类</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Service
@Slf4j
public class SpuServiceImpl implements ISpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Override
    public void addNew(SpuAddNewDTO spuAddNewDTO) {
        // 获取分布式ID
        Long spuId = IdGeneratorUtils.getDistributeId(IdGeneratorUtils.Key.SPU);

        // 获取类别ID
        Long categoryId = spuAddNewDTO.getCategoryId();
        // 查询类别信息
        CategoryStandardVO category = categoryMapper.getById(categoryId);
        // 检查类别是否存在
        if (category == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "新增SPU失败，选择的【类别】不存在！");
        }
        // 检查类别是否启用
        if (category.getEnable() == null || category.getEnable() == 0) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增SPU失败，选择的【类别】已禁用！");
        }
        // 检查类别数据是否完整
        if (category.getParent() == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增SPU失败，选择的【类别】数据不完整，请联系系统管理员！");
        }
        // 检查所提交的类别是否包含子级（不允许在父级类别中创建SPU）
        if (category.getParent().equals(1)) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增SPU失败，选择的【类别】仍包括子级！");
        }

        // 获取品牌ID
        Long brandId = spuAddNewDTO.getBrandId();
        // 查询品牌信息
        BrandStandardVO brand = brandMapper.getById(brandId);
        // 检查品牌是否存在
        if (brand == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "新增SPU失败，选择的【品牌】不存在！");
        }
        // 检查品牌是否启用
        if (brand.getEnable() == null || brand.getEnable() == 0) {
            throw new CoolSharkServiceException(ResponseCode.NOT_ACCEPTABLE, "新增SPU失败，选择的【品牌】已禁用！");
        }

        // 准备SPU数据
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuAddNewDTO, spu);
        spu.setId(spuId);
        spu.setBrandName(brand.getName());
        spu.setCategoryName(category.getName());
        spu.setSales(0);
        spu.setCommentCount(0);
        spu.setPositiveCommentCount(0);
        spu.setSort(spuAddNewDTO.getSort() == null ? DataCommonConst.SORT_DEFAULT : spuAddNewDTO.getSort());
        spu.setDeleted(0);
        spu.setNewArrival(0);
        spu.setRecommend(0);
        spu.setChecked(0);
        spu.setCheckUser(null);
        spu.setGmtCheck(null);
        // 执行插入
        int rows = spuMapper.insert(spu);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增SPU失败，服务器忙，请稍后再次尝试！");
        }

        // 如果还提交了详情，则插入详情
        if (spuAddNewDTO.getDetail() != null) {
            SpuDetail spuDetail = new SpuDetail();
            spuDetail.setSpuId(spuId);
            spuDetail.setDetail(spuAddNewDTO.getDetail());
            rows = spuDetailMapper.insert(spuDetail);
            if (rows != 1) {
                throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "新增SPU失败，服务器忙，请稍后再次尝试！");
            }
        }
    }

    @Override
    public void updateById(Long id, SpuUpdateDTO spuDTO) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuDTO, spu);
        String detail = spuDTO.getDetail();
        if (detail != null && detail.trim().length() > 0) {
            spuDetailMapper.updateDetailBySpuId(spu.getId(), spuDTO.getDetail());
        }
        spuMapper.update(spu);
    }

    @Override
    public void passCheck(Long id) {
        SpuStandardVO spuStandardVO = spuMapper.getById(id);
        if (spuStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "审核失败，尝试访问的数据不存在！");
        }

        if (spuStandardVO.getChecked() == 1) {
            throw new CoolSharkServiceException(ResponseCode.CONFLICT, "审核失败，当前SPU已处于通过审核状态！");
        }

        int rows = spuMapper.updateCheckedById(id, 1);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "审核失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void synchroniseStock(Long id) {
        SpuStandardVO spuStandardVO = spuMapper.getById(id);
        if (spuStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "同步库存失败，尝试访问的SPU数据不存在！");
        }

        int totalStock = skuMapper.sumStockBySpuId(id);
        int rows = spuMapper.updateStockById(id, totalStock);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "同步库存失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void synchronisePrice(Long id) {
        SpuStandardVO spuStandardVO = spuMapper.getById(id);
        if (spuStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "同步价格失败，尝试访问的SPU数据不存在！");
        }

        BigDecimal minPrice = skuMapper.getMinPriceBySpuId(id);
        int rows = spuMapper.updatePriceById(id, minPrice);
        if (rows != 1) {
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, "同步价格失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public SpuStandardVO getById(Long id) {
        SpuStandardVO spuStandardVO = spuMapper.getById(id);
        if (spuStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "查询SPU详情失败，尝试访问的SPU数据不存在！");
        }
        return spuStandardVO;
    }

    @Override
    public JsonPage<SpuListItemVO> list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SpuListItemVO> spuList = spuMapper.list();
        return JsonPage.restPage(new PageInfo<>(spuList));
    }

    @Override
    public JsonPage<SpuListItemVO> listFromDB(SpuQuery spuQuery, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SpuListItemVO> spuList = spuMapper.listByCustomCondition(spuQuery);
        return JsonPage.restPage(new PageInfo<>(spuList));
    }

    @Override
    public JsonPage<SpuStandardVO> listFromES(SpuQuery spuQuery, Integer page, Integer pageSize) {
        throw new RuntimeException("SpuServiceImpl.listFromES() 暂未实现！");
    }

}
