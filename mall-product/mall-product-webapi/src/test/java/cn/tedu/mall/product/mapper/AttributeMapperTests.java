package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Attribute;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class AttributeMapperTests {

    @Autowired
    AttributeMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        // 测试数据
        Long templateId = 1L;
        String name = "颜色";
        String description = "小米手机12 Pro的手机颜色";
        // 执行测试
        Attribute attribute = new Attribute();
        attribute.setTemplateId(1L);
        attribute.setName("颜色");
        attribute.setDescription("小米手机12 Pro的手机颜色");
        int rows = mapper.insert(attribute);
        Long generatedId = attribute.getId();
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        Assertions.assertEquals(DMLConst.RowId.FIRST, generatedId);
        AttributeStandardVO result = mapper.getById(generatedId);
        assertEquals(templateId, result.getTemplateId());
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
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
        Long id = DMLConst.RowId.NOT_EXISTS;
        int rows = mapper.deleteById(id);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateSuccessfully() {
        Long id = 1L;
        Attribute attribute = new Attribute();
        attribute.setId(id);
        attribute.setTemplateId(1L);
        attribute.setName("修改后的颜色");
        attribute.setDescription("修改后的小米手机12 Pro的手机颜色");
        int rows = mapper.update(attribute);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFailureBecauseNotFound() {
        Long id = DMLConst.RowId.NOT_EXISTS;
        Attribute attribute = new Attribute();
        attribute.setId(id);
        attribute.setName("修改后的颜色");
        int rows = mapper.update(attribute);
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
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
    void testGetByIdReturnNullBecauseNotFound() {
        Long id = DMLConst.RowId.NOT_EXISTS;
        Object result = mapper.getById(id);
        assertNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetDetailsByIdSuccessfully() {
        Long id = 1L;
        Object result = mapper.getDetailsById(id);
        assertNotNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetDetailsByIdReturnNullBecauseNotFound() {
        Long id = DMLConst.RowId.NOT_EXISTS;
        Object result = mapper.getDetailsById(id);
        assertNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByTemplateId() {
        Long templateId = 1L;
        List<?> list = mapper.listByTemplateId(templateId);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByTemplateIdAndType() {
        Long templateId = 1L;
        Integer type = 1;
        List<?> list = mapper.listByTemplateIdAndType(templateId, type);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
