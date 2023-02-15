package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class AttributeTemplateServiceTests {

    @Autowired
    IAttributeTemplateService service;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetDetailsByIdSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 1L;
            Object result = service.getDetailsById(id);
            assertNotNull(result);
            log.debug("query result : {}", result);
            log.debug("测试通过！");
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByDetailsByIdFailureBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = DMLConst.RowId.NOT_EXISTS;
            service.getDetailsById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetDetailsIncludeSaleAttributeByIdSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 1L;
            Object result = service.getDetailsIncludeSaleAttributeById(id);
            assertNotNull(result);
            log.debug("query result : {}", result);
            log.debug("测试通过！");
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByDetailsIncludeSaleAttributeByIdFailureBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = DMLConst.RowId.NOT_EXISTS;
            service.getDetailsIncludeSaleAttributeById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetDetailsIncludeNonSaleAttributeByIdSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 1L;
            Object result = service.getDetailsIncludeNonSaleAttributeById(id);
            assertNotNull(result);
            log.debug("query result : {}", result);
            log.debug("测试通过！");
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByDetailsIncludeNonSaleAttributeByIdFailureBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = DMLConst.RowId.NOT_EXISTS;
            service.getDetailsIncludeNonSaleAttributeById(id);
        });
    }

}
