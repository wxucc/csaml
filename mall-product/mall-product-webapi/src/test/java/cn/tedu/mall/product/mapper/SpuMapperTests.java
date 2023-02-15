package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Spu;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
public class SpuMapperTests {

    @Autowired
    SpuMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        Spu spu = new Spu();
        spu.setId(1L); // SPU的id不是自动生成的，需要手动录入
        spu.setName("小米手机12");
        int rows = mapper.insert(spu);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdSuccessfully() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdFailureBecauseNotFound() {
        Long id = 999999999L;
        int rows = mapper.deleteById(id);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateSuccessfully() {
        Long id = 1L;
        Spu spu = new Spu();
        spu.setName("新新新新新小米手机12306");
        spu.setId(id);
        int rows = mapper.update(spu);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFailureBecauseNotFound() {
        Long id = 999999999L;
        Spu spu = new Spu();
        spu.setName("新新新新新小米手机12306");
        spu.setId(id);
        int rows = mapper.update(spu);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateCheckedByIdSuccessfully() {
        Long id = 1L;
        Integer isChecked = 1;
        int rows = mapper.updateCheckedById(id, isChecked);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateCheckedByIdFailureBecauseNotFound() {
        Long id = 999999999L;
        Integer isChecked = 1;
        int rows = mapper.updateCheckedById(id, isChecked);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testCountByAlbumId() {
        Long albumId = 1L; // 由脚本插入，此id下有2条数据
        int count = mapper.countByAlbumId(albumId);
        Assertions.assertEquals(DMLConst.RowCount.FIVE, count);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testCountByBrandId() {
        Long brandId = 1L; // 由脚本插入，此id下有2条数据
        int count = mapper.countByBrandId(brandId);
        Assertions.assertEquals(DMLConst.RowCount.TWO, count);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testCountByTypeNumber() {
        String typeNumber = "MI20121201"; // 由脚本插入，此typeNumber对应1条数据
        int count = mapper.countByTypeNumber(typeNumber);
        Assertions.assertEquals(DMLConst.RowCount.ONE, count);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetByIdSuccessfully() {
        Long id = 1L;
        Object result = mapper.getById(id);
        assertNotNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByIdFailureBecauseNotFound() {
        Long id = 999999999L;
        Object result = mapper.getById(id);
        assertNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByTypeNumber() {
        SpuQuery spuQuery = new SpuQuery();
        spuQuery.setTypeNumber("MI20121201");
        List<?> list = mapper.listByCustomCondition(spuQuery);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByBrandId() {
        SpuQuery spuQuery = new SpuQuery();
        spuQuery.setBrandId(1L);
        List<?> list = mapper.listByCustomCondition(spuQuery);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByCategoryId() {
        SpuQuery spuQuery = new SpuQuery();
        spuQuery.setCategoryId(1L);
        List<?> list = mapper.listByCustomCondition(spuQuery);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
