package cn.tedu.mall.ams.service.impl;

import cn.tedu.mall.ams.exception.CoolSharkException;
import cn.tedu.mall.ams.mapper.RolePermissionMapper;
import cn.tedu.mall.ams.mapper.PermissionMapper;
import cn.tedu.mall.ams.service.IPermissionService;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.admin.dto.PermissionAddDTO;
import cn.tedu.mall.pojo.admin.dto.PermissionUpdateDTO;
import cn.tedu.mall.pojo.admin.model.Permission;
import cn.tedu.mall.pojo.admin.query.PermissionQuery;
import cn.tedu.mall.pojo.admin.vo.PermissionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public void addPermission(PermissionAddDTO permissionAddDTO) {
        //转化对象
        Permission permission=new Permission();
        BeanUtils.copyProperties(permissionAddDTO,permission);
        permissionMapper.insertPermission(permission);
    }

    @Override
    public JsonPage<PermissionVO> listPermissions(PermissionQuery permissionQuery) {
        PageHelper.startPage(permissionQuery.getPageNum(),permissionQuery.getSizeNum());
        List<PermissionVO> permissionVOList=permissionMapper.selectPermissions(permissionQuery);
        PageInfo pageInfo=new PageInfo(permissionVOList);
        return JsonPage.restPage(pageInfo);
    }

    @Override
    public void updatePermission(PermissionUpdateDTO permissionUpdateDTO) {
        Permission permission=new Permission();
        BeanUtils.copyProperties(permissionUpdateDTO,permission);
        permissionMapper.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) {
        //检查是否有关联
        int count=rolePermissionMapper.selectExistByPermissionId(id);
        if(count!=0){
            throw new CoolSharkException("当前权限有关联角色不可删除",409);
        }
        permissionMapper.deletePermission(id);
    }
}
