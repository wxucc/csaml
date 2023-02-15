package cn.tedu.mall.seckill.service;

import cn.tedu.mall.pojo.seckill.dto.SeckillOrderAddDTO;
import cn.tedu.mall.pojo.seckill.vo.SeckillCommitVO;

public interface ISeckillService {
    SeckillCommitVO commitSeckill(SeckillOrderAddDTO seckillOrderAddDTO);
}
