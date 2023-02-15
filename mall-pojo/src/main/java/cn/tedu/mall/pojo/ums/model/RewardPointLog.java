package cn.tedu.mall.pojo.ums.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户积分日志表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data

public class RewardPointLog implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户id
     */

    private Long userId;

    /**
     * 用户名（冗余）
     */

    private String username;

    /**
     * 昵称（冗余）
     */

    private String nickname;

    /**
     * 变动值
     */

    private Integer changeValue;

    /**
     * 变动原因
     */

    private String reason;

    /**
     * 变动时间
     */

    private LocalDateTime gmtChange;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    private LocalDateTime gmtModified;


}
