package cn.tedu.mall.ums.mapper;

import cn.tedu.mall.pojo.ums.model.DeliveryAddress;
import cn.tedu.mall.pojo.ums.vo.DeliveryAddressStandardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> 用户收货地址表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
public interface DeliveryAddressMapper {
    List<DeliveryAddressStandardVO> selectAddressesByUserId(Long userId);

    void insertDeliveryAddress(DeliveryAddress deliveryAddress);

    int selectCountByUserId(@Param("userId")Long userId);

    void updateAddressById(DeliveryAddress deliveryAddress);

    void deleteAddressById(Long id);
}
