package cn.tedu.mall.pojo.ums.model;


import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户基本（常用）信息表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码（冗余，密文）
     */

    private String password;

    /**
     * 昵称
     */

    private String nickname;

    /**
     * 头像URL
     */

    private String avatar;

    /**
     * 手机号码
     */

    private String phone;

    /**
     * 电子邮箱
     */

    private String email;

    /**
     * 是否启用，1=启用，0=未启用
     */

    private Integer enable;

    /**
     * 积分（冗余）
     */

    private Integer rewardPoint;

    /**
     * 最后登录IP地址（冗余）
     */

    private String lastLoginIp;

    /**
     * 累计登录次数（冗余）
     */
    private Integer loginCount;
    /**
     * 最后登录时间（冗余）
     */

    private LocalDateTime gmtLastLogin;
    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
