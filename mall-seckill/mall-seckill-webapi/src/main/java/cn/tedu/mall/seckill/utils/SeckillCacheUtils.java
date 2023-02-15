package cn.tedu.mall.seckill.utils;

import cn.tedu.mall.common.config.PrefixConfiguration;

import java.time.LocalDate;

public class SeckillCacheUtils {
    /**
     * @param spuId 商品spuId
     * @return
     */
    public static String getRandCodeKey(Long spuId){
        return PrefixConfiguration.SeckillPrefixConfiguration.SECKILL_SPU_URL_RAND_CODE_PREFIX+spuId;
    }

    /**
     * @param skuId 商品skuId
     * @param userId 用户id
     * @return
     */
    public static String getReseckillCheckKey(Long skuId,Long userId){
        return PrefixConfiguration.SeckillPrefixConfiguration.SECKILL_RE_SECKILL_PREFIX+skuId+":"+userId;
    }

    /**
     * @param skuId 商品skuId
     * @return
     */
    public static String getStockKey(Long skuId) {
        return PrefixConfiguration.SeckillPrefixConfiguration.SECKILL_SKU_STOCK_PREFIX+skuId;
    }

    public static String getSeckillSpuVOKey(Long spuId) {
        return  PrefixConfiguration.SeckillPrefixConfiguration.SECKILL_SPU_VO_PREFIX+spuId;
    }

    public static String getBloomFilterKey(LocalDate day) {
        return PrefixConfiguration.SpuPrefixConfiguration.SPU_BLOOM_FILTER_PREFIX+day.toString();
    }

    public static String getSeckillSkuVOKey(Long skuId) {
        return PrefixConfiguration.SeckillPrefixConfiguration.SECKILL_SKU_VO_PREFIX+skuId;
    }
}
