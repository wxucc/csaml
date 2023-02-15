package cn.tedu.mall.ams.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.admin.dto.RoleAddDTO;
import cn.tedu.mall.pojo.admin.dto.RoleUpdateDTO;
import cn.tedu.mall.pojo.admin.vo.RoleVO;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface IRoleService {

    JsonPage<RoleVO> listRoles(Integer pageNum, Integer sizeNum);

    JsonPage<RoleVO> queryRoles(Integer pageNum, Integer sizeNum, String query);

    void addRole(RoleAddDTO roleAddDTO);

    void updateRole(RoleUpdateDTO roleUpdateDTO);

    void deleteRole(Long id);
}
