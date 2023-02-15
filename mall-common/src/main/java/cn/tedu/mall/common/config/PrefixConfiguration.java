package cn.tedu.mall.common.config;


public class PrefixConfiguration {
    /*前缀命名的规则由以下部分组成:
    1.变量名都是常量大写,以_链接,例如 SPU
    2.变量名根据对应保存的业务数据javabean定义变量名称,如Spu 对应SPU_ SpuSimpleVO 对应SPU_SIMPLE_VO
    3.变量名最后全部拼接PREFIX 例如 SPU_PREFIX SPU_SIMPLE_VO_PREFIX
    4.变量名在javabean名字和PREFIX之间写业务逻辑 例如Spu锁,SPU_LOCK_PREFIX
    5.变量值都是key前缀,使用_链接,以_结束,就是变量名称小写,去掉prefix,在业务中使用后面拼接唯一值,例如
    Spu的缓存数据 public static final String SPU_PREFIX="spu_"
    6.在特殊情况下,允许业务层自定义key值*/


    public static class SpuPrefixConfiguration{
        //spu 返回对象SpuSimpleVO的前缀 后面拼接spuId
        public static final String SPU_STANDARD_VO_PREFIX="mall:spu:standard:vo:";
        public static final String SPU_PREFIX="spu:";
        public static final String SPU_BLOOM_FILTER_PREFIX="spu:bloom:filter:";
    }
    public static class SkuPrefixConfiguration{
        public static final String SKU_SIMPLE_VO_PREFIX="sku:simple:vo:";
        public static final String SKU_PREFIX="sku:";
    }
    public static class SeckillPrefixConfiguration{
        public static final String SECKILL_RE_SECKILL_PREFIX="mall:seckill:reseckill:";
        public static final String SECKILL_SPU_PREFIX="mall:seckill:spu:";
        public static final String SECKILL_SPU_VO_PREFIX="mall:seckill:spu:vo:";
        public static final String SECKILL_SPU_URL_RAND_CODE_PREFIX="mall:seckill:spu:url:rand:code:";
        //秒杀商品库存
        public static final String SECKILL_SKU_STOCK_PREFIX="mall:seckill:sku:stock:";
        public static final String SECKILL_SKU_VO_PREFIX = "mall:seckill:sku:vo:";

    }
}
