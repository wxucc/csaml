package cn.tedu.mall.ums.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressAddDTO;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressEditDTO;
import cn.tedu.mall.pojo.ums.vo.DeliveryAddressStandardVO;

/**
 * <p>
 * 用户收货地址表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */

public interface IDeliveryAddressService{

    JsonPage<DeliveryAddressStandardVO> listAddress(Integer page, Integer pageSize);

    void addAddress(DeliveryAddressAddDTO deliveryAddressAddDTO);

    void editAddress(DeliveryAddressEditDTO deliveryAddressEditDTO);

    void deleteAddress(Long id);
}
