package cn.tedu.mall.seckill.mapper;

import cn.tedu.mall.pojo.seckill.model.Success;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessMapper {
    //声明新增Success类型对象到数据库的方法
    int saveSuccess(Success success);
}
