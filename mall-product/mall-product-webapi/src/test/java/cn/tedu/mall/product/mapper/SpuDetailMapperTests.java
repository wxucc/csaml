package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.SpuDetail;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
public class SpuDetailMapperTests {

    @Autowired
    SpuDetailMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(1L);
        spuDetail.setDetail("1号SPU的详情");
        int rows = mapper.insert(spuDetail);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        Assertions.assertEquals(DMLConst.RowId.FIRST, spuDetail.getId());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteBySpuIdSuccessfully() {
        Long id = 1L;
        int rows = mapper.deleteBySpuId(id);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteBySpuIdFailureBecauseNotFound() {
        Long id = 999999999L;
        int rows = mapper.deleteBySpuId(id);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateSuccessfully() {
        Long id = 1L;
        String detail = "1号SPU的新新新新新详情";
        int rows = mapper.updateDetailBySpuId(id, detail);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFailureBecauseNotFound() {
        Long id = 999999999L;
        String detail = "1号SPU的新新新新新详情";
        int rows = mapper.updateDetailBySpuId(id, detail);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetBySpuIdSuccessfully() {
        Long id = 1L;
        Object result = mapper.getBySpuId(id);
        assertNotNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetBySpuIdFailureBecauseNotFound() {
        Long id = 999999999L;
        Object result = mapper.getBySpuId(id);
        assertNull(result);
        log.debug("测试通过！");
    }

}
