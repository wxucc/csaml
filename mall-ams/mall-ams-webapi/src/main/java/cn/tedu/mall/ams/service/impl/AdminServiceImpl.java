package cn.tedu.mall.ams.service.impl;

import cn.tedu.mall.ams.exception.CoolSharkException;
import cn.tedu.mall.ams.mapper.AdminRoleMapper;
import cn.tedu.mall.ams.mapper.AdminMapper;
import cn.tedu.mall.ams.service.IAdminService;
import cn.tedu.mall.ams.utils.IdGeneratorUtils;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.admin.dto.AdminAddDTO;
import cn.tedu.mall.pojo.admin.dto.AdminUpdateDTO;
import cn.tedu.mall.pojo.admin.model.Admin;
import cn.tedu.mall.pojo.admin.vo.AdminVO;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Service
public class AdminServiceImpl implements IAdminService {


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void addAdmin(AdminAddDTO adminDTO) {
        checkPassword(adminDTO.getPassword(),adminDTO.getPasswordAct());
        //转化bean
        Admin admin= new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        //补充id
        Long id= IdGeneratorUtils.generatId("admin");
        admin.setId(id);
        //密码加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.insertAdmin(admin);
    }

    @Override
    public JsonPage<AdminVO> queryAdmins(Integer pageNum, Integer sizeNum, String query) {
        if(StringUtils.isEmpty(query)){
            return listAdmins(pageNum,sizeNum);
        }
        PageHelper.startPage(pageNum,sizeNum);
        List<AdminVO> adminVOList=adminMapper.selectAdminsByUsername(query);
        PageInfo pageInfo=PageInfo.of(adminVOList);
        return JsonPage.restPage(pageInfo);
    }

    @Override
    public void updateAdmin(AdminUpdateDTO adminUpdateDTO) {
        Admin admin=new Admin();
        BeanUtils.copyProperties(adminUpdateDTO,admin);
        checkPassword(adminUpdateDTO.getPassword(),adminUpdateDTO.getPasswordAct());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminMapper.deleteAdmin(id);
        adminRoleMapper.deleteAdminRole(id);
    }

    @Override
    public AdminVO queryOneAdmin(String username) {
        return adminMapper.selectAdminByUsername(username);
    }

    //检查密码
    private void checkPassword(String password,String passwordAct) {
        if(!StringUtils.equals(password,passwordAct)){
            throw new CoolSharkException("两次输入密码不正确",400);
        }
    }

    @Override
    public JsonPage<AdminVO> listAdmins(Integer pageNum, Integer sizeNum) {
        PageHelper.startPage(pageNum,sizeNum);
        List<AdminVO> adminVOList=adminMapper.selectAdmins();
        PageInfo pageInfo=PageInfo.of(adminVOList);
        return JsonPage.restPage(pageInfo);

    }
}
