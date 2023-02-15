package cn.tedu.mall.ams.mapper;

import cn.tedu.mall.pojo.admin.model.Admin;
import cn.tedu.mall.pojo.admin.vo.AdminVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p> 管理员表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface AdminMapper extends BaseMapper<Admin> {
    void insertAdmin(Admin admin);

    List<AdminVO> selectAdmins();

    List<AdminVO> selectAdminsByUsername(String query);

    AdminVO selectAdminByUsername(String username);

    void updateAdmin(Admin admin);

    void deleteAdmin(Long id);
}
