package cn.tedu.mall.ums.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressAddDTO;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressEditDTO;
import cn.tedu.mall.pojo.ums.model.DeliveryAddress;
import cn.tedu.mall.pojo.ums.vo.DeliveryAddressStandardVO;
import cn.tedu.mall.ums.mapper.DeliveryAddressMapper;
import cn.tedu.mall.ums.service.IDeliveryAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Service
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public JsonPage<DeliveryAddressStandardVO> listAddress(Integer page, Integer pageSize) {
        Long userId = getUserId();
        PageHelper.startPage(page,pageSize);
        List<DeliveryAddressStandardVO> addresses=deliveryAddressMapper.selectAddressesByUserId(userId);
        return JsonPage.restPage(new PageInfo<>(addresses));
    }

    @Override
    public void addAddress(DeliveryAddressAddDTO deliveryAddressAddDTO) {
        //转化数据
        Long userId = getUserId();
        DeliveryAddress deliveryAddress=new DeliveryAddress();
        //查询已存在
        int count=deliveryAddressMapper.selectCountByUserId(userId);
        if(count==0){
            //说明之前米有写入任何地址管理,第一个地址就是默认地址
            deliveryAddress.setDefaultAddress(1);
        }else{
            deliveryAddress.setDefaultAddress(0);
        }
        BeanUtils.copyProperties(deliveryAddressAddDTO,deliveryAddress);
        deliveryAddress.setUserId(userId);
        deliveryAddressMapper.insertDeliveryAddress(deliveryAddress);
    }

    @Override
    public void editAddress(DeliveryAddressEditDTO deliveryAddressEditDTO) {
        //转化
        DeliveryAddress deliveryAddress=new DeliveryAddress();
        BeanUtils.copyProperties(deliveryAddressEditDTO,deliveryAddress);
        deliveryAddressMapper.updateAddressById(deliveryAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        deliveryAddressMapper.deleteAddressById(id);
    }

    //TODO 可以和购物车业务层方法合并简化
    public CsmallAuthenticationInfo getUserInfo(){
        //从security环境获取username,先拿到authentication
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //如果不是空的可以调用dubbo远程微服务获取adminVO
        if(authentication!=null){
            CsmallAuthenticationInfo csmallAuthenticationInfo=(CsmallAuthenticationInfo)authentication.getCredentials();
            return csmallAuthenticationInfo;
        }else{
            throw new CoolSharkServiceException(ResponseCode.UNAUTHORIZED,"没有登录者信息");
        }
    }
    public Long getUserId(){
        CsmallAuthenticationInfo userInfo = getUserInfo();
        return userInfo.getId();
    }
}
