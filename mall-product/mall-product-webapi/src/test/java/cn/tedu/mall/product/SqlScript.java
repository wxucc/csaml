package cn.tedu.mall.product;

public class SqlScript {

    /**
     * 清空所有数据表的SQL脚本
     *
     * @see SqlScript#INSERT_ALL_TEST_DATA
     */
    public static final String TRUNCATE_ALL_TABLE = "classpath:sql/truncate_all.sql";

    /**
     * 向所有数据表中插入测试数据的SQL脚本
     *
     * @see SqlScript#TRUNCATE_ALL_TABLE
     */
    public static final String INSERT_ALL_TEST_DATA = "classpath:sql/insert_all_test_data.sql";

}
