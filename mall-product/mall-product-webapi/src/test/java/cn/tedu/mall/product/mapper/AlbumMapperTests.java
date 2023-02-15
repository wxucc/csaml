package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Album;
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
public class AlbumMapperTests {

    @Autowired
    AlbumMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        Album album = new Album();
        album.setName("小米手机12的相册");
        album.setDescription("小米手机12的相册的描述");
        album.setSort(1);
        int rows = mapper.insert(album);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        Assertions.assertEquals(DMLConst.RowId.FIRST, album.getId());
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
        Album album = new Album();
        album.setId(id);
        album.setName("修改后的小米手机12 Pro的相册");
        album.setDescription("修改后的小米手机12 Pro的相册的描述");
        album.setSort(99);
        int rows = mapper.update(album);
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFailureBecauseNotFound() {
        Long id = 999999999L;
        Album album = new Album();
        album.setId(id);
        album.setName("修改后的小米手机12 Pro的相册");
        int rows = mapper.update(album);
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
    void testGetByIdFailureBecauseNotFound() {
        Long id = 999999999L;
        Object result = mapper.getById(id);
        assertNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testList() {
        List<?> list = mapper.list();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
