package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.model.Spu;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.product.service.front.IForFrontSpuService;
import cn.tedu.mall.product.mapper.SpuDetailMapper;
import cn.tedu.mall.product.mapper.SpuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Service
public class ForFrontSpuServiceImpl implements IForFrontSpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Override
    public JsonPage<SpuListItemVO> listSpuByCategoryId(Long categoryId, Integer page, Integer pageSize) {
        SpuQuery spuQuery=new SpuQuery();
        spuQuery.setCategoryId(categoryId);
        PageHelper.startPage(page,pageSize);
        List<SpuListItemVO> spuListItemVOs = spuMapper.listByCustomCondition(spuQuery);
        return JsonPage.restPage(new PageInfo<>(spuListItemVOs));
    }

    /**
     * 和已有方法重复
     * @param id
     * @return
     */
    @Override
    public SpuStandardVO getSpuById(Long id) {
        SpuStandardVO spuStandardVO = spuMapper.getById(id);
        if (spuStandardVO == null) {
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND, "查询SPU详情失败，尝试访问的SPU数据不存在！");
        }
        return spuStandardVO;
    }

    @Override
    public SpuDetailStandardVO getSpuDetailById(Long spuId) {
        SpuDetailStandardVO spuDetailStandardVO = spuDetailMapper.getBySpuId(spuId);
        return spuDetailStandardVO;
    }

    @Override
    public JsonPage<Spu> getSpuByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Spu> list=spuMapper.findAllList();
        return JsonPage.restPage(new PageInfo<>(list));
    }
}





