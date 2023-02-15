package cn.tedu.mall.ams.service.impl;

import cn.tedu.mall.ams.exception.CoolSharkException;
import cn.tedu.mall.ams.mapper.AdminRoleMapper;
import cn.tedu.mall.ams.mapper.RoleMapper;
import cn.tedu.mall.ams.service.IRoleService;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.admin.dto.RoleAddDTO;
import cn.tedu.mall.pojo.admin.dto.RoleUpdateDTO;
import cn.tedu.mall.pojo.admin.model.Role;
import cn.tedu.mall.pojo.admin.vo.RoleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Override
    public JsonPage<RoleVO> listRoles(Integer pageNum, Integer sizeNum) {
        PageHelper.startPage(pageNum,sizeNum);
        List<RoleVO> roles=roleMapper.selectRoles();
        PageInfo pageInfo=new PageInfo<>(roles);
        return JsonPage.restPage(pageInfo);
    }

    @Override
    public JsonPage<RoleVO> queryRoles(Integer pageNum, Integer sizeNum, String query) {
        PageHelper.startPage(pageNum,sizeNum);
        List<RoleVO> roles=roleMapper.selectRolesLikeName(query);
        PageInfo pageInfo=new PageInfo<>(roles);
        return JsonPage.restPage(pageInfo);
    }

    @Override
    public void addRole(RoleAddDTO roleAddDTO) {
        Role role=new Role();
        BeanUtils.copyProperties(roleAddDTO,role);
        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(RoleUpdateDTO roleUpdateDTO) {
        Role role=new Role();
        BeanUtils.copyProperties(roleUpdateDTO,role);
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        int exist = roleMapper.selectExistRoleById(id);
        if(exist==0){
            throw new CoolSharkException("当前删除的角色不存在:"+id,409);
        }
        int count=adminRoleMapper.selectRelationByRoleid(id);
        if(count!=0){
            throw new CoolSharkException("当前角色关联了账号无法删除",409);
        }
        roleMapper.deleteRol(id);
    }
}
