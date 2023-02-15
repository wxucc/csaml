package cn.tedu.mall.sso.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * <p> 权限表 Mapper 接口</p>
 *
 * <p>2021-12-14修改</p>
 * <ul>
 *     <li>代码排版，添加注释</li>
 *     <li>添加@Repository注解</li>
 *     <li>把selectPermissionsByAdminId()参数名称从id改为adminId</li>
 *     <li>把selectPermissionsByAdminId()返回值List元素类型从Permission改为String</li>
 *     <li>修改对应的SQL，添加distinct，避免查询结果的权限是重复的，无意义</li>
 *     <li>把selectPermissionsByAdminId()方法名改为findPermissionsByAdminId</li>
 * </ul>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Qualifier("db1SqlSessionTemplate")
public interface PermissionMapper{

    /**
     * 根据管理员Id查询管理员的权限列表
     *
     * @param adminId 管理员id
     * @return 该管理员匹配的权限列表，如果没有匹配的数据，将返回长度为0的List集合
     */
    List<String> findPermissionsByAdminId(@Param("adminId") Long adminId);

}
