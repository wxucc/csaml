package cn.tedu.mall.ums.mapper;

import cn.tedu.mall.pojo.ums.model.UserDetail;
import cn.tedu.mall.pojo.ums.vo.UserDetailStandardVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p> 用户详细（不常用）信息表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
public interface UserDetailMapper{
    void insertUserDetail(UserDetail userDetail);

    UserDetailStandardVO selectUserDetailByUserId(@Param("userId") Long userId);

    void updateUserDetailById(UserDetail userDetail);

    UserDetailStandardVO selectUserDetailById(Long id);
}
