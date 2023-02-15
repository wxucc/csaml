package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class AttributeServiceTests {

    @Autowired
    IAttributeService service;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetDetailsByIdSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 1L;
            Object result = service.getDetailsById(id);
            assertNotNull(result);
            log.debug("测试通过！");
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetDetailsByIdReturnNullBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = DMLConst.RowId.NOT_EXISTS;
            service.getDetailsById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByTemplateId() {
        Long templateId = 1L;
        Integer page = 1;
        Integer pageSize = 5;
        JsonPage<?> jsonPage = service.listByTemplateId(templateId, page, pageSize);
        List<?> list = jsonPage.getList();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListIncludeSaleAttributeByTemplateId() {
        Long templateId = 1L;
        Integer page = 1;
        Integer pageSize = 5;
        JsonPage<?> jsonPage = service.listSaleAttributesByTemplateId(templateId, page, pageSize);
        List<?> list = jsonPage.getList();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListIncludeNonSaleAttributeByTemplateId() {
        Long templateId = 1L;
        Integer page = 1;
        Integer pageSize = 5;
        JsonPage<?> jsonPage = service.listNonSaleAttributesByTemplateId(templateId, page, pageSize);
        List<?> list = jsonPage.getList();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
