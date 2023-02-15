package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Picture;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
public class PictureMapperTests {

    @Autowired
    PictureMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertBatchSuccessfully() {
        List<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Picture picture = new Picture();
            picture.setAlbumId(1L);
            picture.setUrl("http://www.tedu.cn/" + (i + 1) + ".jpg");
            picture.setWidth(500);
            picture.setHeight(500);
            pictures.add(picture);
        }
        int rows = mapper.insertBatch(pictures);
        Assertions.assertEquals(DMLConst.AffectedRows.BATCH_TEN, rows);
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
    void testCountByAlbumId() {
        Long albumId = 1L; // 由脚本插入，此id下有2条数据
        int count = mapper.countByAlbumId(albumId);
        Assertions.assertEquals(DMLConst.RowCount.THREE, count);
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
    void testListByAlbumId() {
        Long albumId = 1L; // 由脚本插入，此id下有数据
        List<?> list = mapper.listByAlbumId(albumId);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
