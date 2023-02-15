package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Category;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
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
public class CategoryMapperTests {

    @Autowired
    CategoryMapper mapper;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        // 测试数据
        String categoryName = "智能电器";
        String categoryKeywords = "智能电器,智能家电";
        String categoryIcon = "http://www.tedu.cn/logo.png";
        Integer categorySort = 99;
        // 执行测试
        Category category = new Category();
        category.setName(categoryName);
        category.setKeywords(categoryKeywords);
        category.setIcon(categoryIcon);
        category.setSort(categorySort);
        int rows = mapper.insert(category);
        Long generatedId = category.getId();
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        Assertions.assertEquals(DMLConst.RowId.FIRST, generatedId);
        CategoryStandardVO result = mapper.getById(generatedId);
        assertEquals(categoryName, result.getName());
        assertEquals(categoryKeywords, result.getKeywords());
        assertEquals(categoryIcon, result.getIcon());
        assertEquals(categorySort, result.getSort());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 执行测试
        int rows = mapper.deleteById(id);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 执行测试
        int rows = mapper.deleteById(id);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateParentByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        Integer isParent = 1;
        // 执行测试
        int rows = mapper.updateParentById(id, isParent);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        CategoryStandardVO result = mapper.getById(id);
        assertEquals(isParent, result.getParent());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateParentByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        Integer isParent = 1;
        // 执行测试
        int rows = mapper.updateParentById(id, isParent);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateEnableByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        Integer enable = 1;
        // 执行测试
        int rows = mapper.updateEnableById(id, enable);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        CategoryStandardVO result = mapper.getById(id);
        assertEquals(enable, result.getEnable());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateEnableByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        Integer enable = 1;
        // 执行测试
        int rows = mapper.updateEnableById(id, enable);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateDisplayByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        Integer isDisplay = 1;
        // 执行测试
        int rows = mapper.updateDisplayById(id, isDisplay);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        CategoryStandardVO result = mapper.getById(id);
        assertEquals(isDisplay, result.getDisplay());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateDisplayByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        Integer isDisplay = 1;
        // 执行测试
        int rows = mapper.updateDisplayById(id, isDisplay);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdSuccessfully() {
        // 测试数据
        Long categoryId = 1L;
        String categoryName = "智能电器";
        String categoryKeywords = "智能电器,智能家电";
        String categoryIcon = "http://www.tedu.cn/logo.png";
        Integer categorySort = 99;
        // 执行更新
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        category.setKeywords(categoryKeywords);
        category.setIcon(categoryIcon);
        category.setSort(categorySort);
        int rows = mapper.updateBaseInfoById(category);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        CategoryStandardVO result = mapper.getById(categoryId);
        assertEquals(categoryName, result.getName());
        assertEquals(categoryKeywords, result.getKeywords());
        assertEquals(categoryIcon, result.getIcon());
        assertEquals(categorySort, result.getSort());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdFailureBecauseNotFound() {
        // 测试数据
        Long categoryId = DMLConst.RowId.NOT_EXISTS;
        String categoryName = "智能电器";
        // 执行更新
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rows = mapper.updateBaseInfoById(category);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ZERO, rows);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdSuccessfully() {
        // 测试数据
        Long categoryId = 1L;
        String categoryName = "智能电器";
        String categoryKeywords = "智能电器,智能家电";
        String categoryIcon = "http://www.tedu.cn/logo.png";
        Integer categoryEnable = 1;
        Integer categoryIsDisplay = 1;
        Integer categorySort = 99;
        // 执行更新
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        category.setKeywords(categoryKeywords);
        category.setIcon(categoryIcon);
        category.setEnable(categoryEnable);
        category.setDisplay(categoryIsDisplay);
        category.setSort(categorySort);
        int rows = mapper.updateFullInfoById(category);
        // 断言结果
        Assertions.assertEquals(DMLConst.AffectedRows.ONE, rows);
        CategoryStandardVO result = mapper.getById(categoryId);
        assertEquals(categoryName, result.getName());
        assertEquals(categoryKeywords, result.getKeywords());
        assertEquals(categoryIcon, result.getIcon());
        assertEquals(categoryEnable, result.getEnable());
        assertEquals(categoryIsDisplay, result.getDisplay());
        assertEquals(categorySort, result.getSort());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdFailureBecauseNotFound() {
        // 测试数据
        Long categoryId = DMLConst.RowId.NOT_EXISTS;
        String categoryName = "智能电器";
        // 执行更新
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rows = mapper.updateFullInfoById(category);
        // 断言结果
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
        Long id = DMLConst.RowId.NOT_EXISTS;
        Object result = mapper.getById(id);
        assertNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetByNameSuccessfully() {
        String name = "手机";
        Object result = mapper.getByName(name);
        assertNotNull(result);
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByNameFailureBecauseNotFound() {
        String name = "不存在";
        Object result = mapper.getByName(name);
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

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByBrandId() {
        Long brandId = 1L; // 由脚本插入，此id下有数据
        List<?> list = mapper.listByBrandId(brandId);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByParentId() {
        Long parentId = 1L; // 由脚本插入，此id下有数据
        List<?> list = mapper.listByParentId(parentId);
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}
