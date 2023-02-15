package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.product.SqlScript;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class SpuServiceTests {

    @Autowired
    ISpuService service;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCheckSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 1L; // 此测试数据存在，且是【未通过】审核状态
            service.passCheck(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCheckFailureBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = 999999999L;
            service.passCheck(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCheckFailureBecauseAlreadyChecked() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = 5L; // 此测试数据存在，且是【已通过】审核状态
            service.passCheck(id);
        });
    }

}
