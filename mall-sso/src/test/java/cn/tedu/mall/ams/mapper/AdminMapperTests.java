package cn.tedu.mall.ams.mapper;

import cn.tedu.mall.ams.SqlScript;
import cn.tedu.mall.sso.mapper.admin.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class AdminMapperTests {

    @Autowired
    AdminMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetByUsernameSuccessfully() {
        String username = "admin";
        Object result = mapper.findByUsername(username);
        assertNotNull(result);
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByUsernameFailureBecauseNotFound() {
        String username = "NO_BODY";
        Object result = mapper.findByUsername(username);
        assertNull(result);
    }

}
