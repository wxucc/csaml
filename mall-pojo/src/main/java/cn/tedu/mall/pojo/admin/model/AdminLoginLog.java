package cn.tedu.mall.pojo.admin.model;


import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员登录日志表
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Data
public class AdminLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 管理员id
     */

    private Long adminId;

    /**
     * 管理员用户名（冗余）
     */

    private String username;

    /**
     * 管理员昵称（冗余）
     */

    private String nickname;

    /**
     * 登录IP地址
     */

    private String ip;

    /**
     * 浏览器内核
     */

    private String userAgent;

    /**
     * 登录时间
     */

    private LocalDateTime gmtLogin;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;


}
