package cn.tedu.mall.product.service;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.CategoryAddNewDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateBaseInfoDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
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
public class CategoryServiceTests {

    @Autowired
    ICategoryService service;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewSuccessfully() {
        // 测试数据
        String categoryName = "智能电器";
        String categoryKeywords = "智能电器,智能家电";
        String categoryIcon = "http://www.tedu.cn/logo.png";
        Long categoryParentId = 0L;
        Integer categoryEnable = 1;
        Integer categoryIsDisplay = 1;
        Integer categorySort = 99;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
            categoryAddNewDTO.setName(categoryName);
            categoryAddNewDTO.setKeywords(categoryKeywords);
            categoryAddNewDTO.setIcon(categoryIcon);
            categoryAddNewDTO.setParentId(categoryParentId);
            categoryAddNewDTO.setEnable(categoryEnable);
            categoryAddNewDTO.setDisplay(categoryIsDisplay);
            categoryAddNewDTO.setSort(categorySort);
            Long generatedId = service.addNew(categoryAddNewDTO);
            // 断言结果
            assertEquals(DMLConst.RowId.FIRST, generatedId);
            CategoryStandardVO result = service.getById(generatedId);
            assertEquals(categoryName, result.getName());
            assertEquals(categoryKeywords, result.getKeywords());
            assertEquals(categoryIcon, result.getIcon());
            assertEquals(categoryParentId, result.getParentId());
            assertEquals(categoryEnable, result.getEnable());
            assertEquals(categoryIsDisplay, result.getDisplay());
            assertEquals(categorySort, result.getSort());
            assertEquals(0, result.getParent());
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewChildSuccessfully() {
        // 测试数据
        Long parentCategoryId = 1L; // 此数据由SQL脚本插入
        String childCategoryName = "老年机";
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试 -- 新增子级类别
            CategoryAddNewDTO childCategoryAddNewDTO = new CategoryAddNewDTO();
            childCategoryAddNewDTO.setName(childCategoryName);
            childCategoryAddNewDTO.setParentId(parentCategoryId);
            Long childGeneratedId = service.addNew(childCategoryAddNewDTO);
            // 断言结果 -- 父级类别相关数据
            CategoryStandardVO parentCategory = service.getById(parentCategoryId);
            assertEquals(1, parentCategory.getParent());
            assertEquals(1, parentCategory.getDepth());
            // 断言结果 -- 子级类别相关数据
            CategoryStandardVO childCategory = service.getById(childGeneratedId);
            assertEquals(2, childCategory.getDepth());
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewFailedBecauseNameConflict() {
        // 测试数据
        String name = "手机";
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
            categoryAddNewDTO.setName(name);
            service.addNew(categoryAddNewDTO);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewFailedBecauseParentNotFound() {
        // 测试数据
        String categoryName = "智能电器";
        Long categoryParentId = DMLConst.RowId.NOT_EXISTS;
        // 执行测试
        CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
        categoryAddNewDTO.setName(categoryName);
        categoryAddNewDTO.setParentId(categoryParentId);
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            service.addNew(categoryAddNewDTO);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdSuccessfully() {
        assertDoesNotThrow(() -> {
            Long id = 10L; // 此数据由SQL脚本插入，无子级类别
            service.deleteById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testDeleteByIdFailedBecauseNotFound() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = DMLConst.RowId.NOT_EXISTS;
            service.deleteById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testDeleteByIdFailedBecauseHasChild() {
        assertThrows(CoolSharkServiceException.class, () -> {
            Long id = 1L; // 此数据由SQL脚本插入，存在子级类别
            service.deleteById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdSuccessfully() {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"禁用"状态
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            service.setEnableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setEnableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdFailureBecauseStateConflict() {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"启用"状态
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setEnableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdSuccessfully() {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"启用"状态
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            service.setDisableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setDisableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdFailureBecauseStateConflict() {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"禁用"状态
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setDisableById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdSuccessfully() {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"隐藏"状态
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            service.setDisplayById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setDisplayById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdFailureBecauseStateConflict() {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"显示"状态
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setDisplayById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdSuccessfully() {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"显示"状态
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行测试
            service.setHiddenById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setHiddenById(id);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdFailureBecauseStateConflict() {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"隐藏"状态
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            // 执行测试
            service.setHiddenById(id);
        });
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
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行更新
            CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO = new CategoryUpdateBaseInfoDTO();
            categoryUpdateBaseInfoDTO.setName(categoryName);
            categoryUpdateBaseInfoDTO.setKeywords(categoryKeywords);
            categoryUpdateBaseInfoDTO.setIcon(categoryIcon);
            categoryUpdateBaseInfoDTO.setSort(categorySort);
            service.updateBaseInfoById(categoryId, categoryUpdateBaseInfoDTO);
            // 断言结果
            CategoryStandardVO result = service.getById(categoryId);
            assertEquals(categoryName, result.getName());
            assertEquals(categoryKeywords, result.getKeywords());
            assertEquals(categoryIcon, result.getIcon());
            assertEquals(categorySort, result.getSort());
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdFailureBecauseNotFound() {
        // 测试数据
        Long categoryId = DMLConst.RowId.NOT_EXISTS;
        String categoryName = "智能电器";
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO = new CategoryUpdateBaseInfoDTO();
            categoryUpdateBaseInfoDTO.setName(categoryName);
            service.updateBaseInfoById(categoryId, categoryUpdateBaseInfoDTO);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdFailureBecauseNameConflict() {
        // 测试数据
        Long categoryId = 1L;
        String categoryName = "电脑"; // 此名称已存在于其它记录中，由SQL脚本插入
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO = new CategoryUpdateBaseInfoDTO();
            categoryUpdateBaseInfoDTO.setName(categoryName);
            service.updateBaseInfoById(categoryId, categoryUpdateBaseInfoDTO);
        });
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
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            // 执行更新
            CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO = new CategoryUpdateFullInfoDTO();
            categoryUpdateFullInfoDTO.setName(categoryName);
            categoryUpdateFullInfoDTO.setKeywords(categoryKeywords);
            categoryUpdateFullInfoDTO.setIcon(categoryIcon);
            categoryUpdateFullInfoDTO.setSort(categorySort);
            categoryUpdateFullInfoDTO.setEnable(categoryEnable);
            categoryUpdateFullInfoDTO.setDisplay(categoryIsDisplay);
            service.updateFullInfoById(categoryId, categoryUpdateFullInfoDTO);
            // 断言结果
            CategoryStandardVO result = service.getById(categoryId);
            assertEquals(categoryName, result.getName());
            assertEquals(categoryKeywords, result.getKeywords());
            assertEquals(categoryIcon, result.getIcon());
            assertEquals(categoryEnable, result.getEnable());
            assertEquals(categoryIsDisplay, result.getDisplay());
            assertEquals(categorySort, result.getSort());
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdFailureBecauseNotFound() {
        // 测试数据
        Long categoryId = DMLConst.RowId.NOT_EXISTS;
        String categoryName = "智能电器";
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO = new CategoryUpdateFullInfoDTO();
            categoryUpdateFullInfoDTO.setName(categoryName);
            service.updateFullInfoById(categoryId, categoryUpdateFullInfoDTO);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdFailureBecauseNameConflict() {
        // 测试数据
        Long categoryId = 1L;
        String categoryName = "电脑"; // 此名称已存在于其它记录中，由SQL脚本插入
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO = new CategoryUpdateFullInfoDTO();
            categoryUpdateFullInfoDTO.setName(categoryName);
            service.updateFullInfoById(categoryId, categoryUpdateFullInfoDTO);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 断言不会抛出异常
        assertDoesNotThrow(() -> {
            Object result = service.getById(id);
            assertNotNull(result);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByIdFailureBecauseNotFound() {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 断言将抛出异常
        assertThrows(CoolSharkServiceException.class, () -> {
            service.getById(id);
        });
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByBrandId() {
        // 测试数据
        Long brandId = 1L; // 由脚本插入，此id下有数据
        Integer page = 1;
        Integer pageSize = 5;
        // 执行测试
        JsonPage<?> jsonPage = service.listByBrand(brandId, page, pageSize);
        List<?> list = jsonPage.getList();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByParentId() {
        // 测试数据
        Long parentId = 1L; // 由脚本插入，此id下有数据
        Integer page = 1;
        Integer pageSize = 5;
        // 执行测试
        JsonPage<?> jsonPage = service.listByParent(parentId, page, pageSize);
        List<?> list = jsonPage.getList();
        log.debug("记录数：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

}