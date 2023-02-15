package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.product.SqlScript;
import cn.tedu.mall.product.constant.DMLConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class CategoryControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewSuccessfully() throws Exception {
        // 测试数据
        String categoryName = "智能电器";
        String categoryKeywords = "智能电器,智能家电";
        String categoryIcon = "http://www.tedu.cn/logo.png";
        String categoryParentId = "0";
        String categoryEnable = "1";
        String categoryIsDisplay = "1";
        String categorySort = "99";
        // 请求路径
        String url = "/pms/categories/addnew";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", categoryName)
                .param("keywords", categoryKeywords)
                .param("icon", categoryIcon)
                .param("parentId", categoryParentId)
                .param("enable", categoryEnable)
                .param("isDisplay", categoryIsDisplay)
                .param("sort", categorySort)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewFailedBecauseNameConflict() throws Exception {
        // 测试数据
        String categoryName = "手机"; // 此数据已由SQL脚本插入
        // 请求路径
        String url = "/pms/categories/addnew";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", categoryName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewFailedBecauseParentNotFound() throws Exception {
        // 测试数据
        String categoryName = "智能电器";
        String categoryParentId = "" + DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/addnew";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", categoryName)
                .param("parentId", categoryParentId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testDeleteByIdSuccessfully() throws Exception {
        // 测试数据
        String id = "" + 10L; // 此数据由SQL脚本插入，无子级类别
        // 请求路径
        String url = "/pms/categories/" + id + "/delete";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testDeleteByIdFailedBecauseNotFound() throws Exception {
        // 测试数据
        String id = "" + DMLConst.RowId.NOT_EXISTS; // 此数据由SQL脚本插入，无子级类别
        // 请求路径
        String url = "/pms/categories/" + id + "/delete";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testDeleteByIdFailedBecauseHasChild() throws Exception {
        // 测试数据
        String id = "" + 1L; // 此数据由SQL脚本插入，无子级类别
        // 请求路径
        String url = "/pms/categories/" + id + "/delete";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdSuccessfully() throws Exception {
        // 测试数据
        String id = "" + 10L; // 此数据由SQL脚本插入，且处于"禁用"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/enable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        String id = "" + DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/" + id + "/status/enable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetEnableByIdFailureBecauseStateConflict() throws Exception {
        // 测试数据
        String id = "" + 1L;// 此数据由SQL脚本插入，且处于"启用"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/enable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"启用"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/disable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/" + id + "/status/disable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisableByIdFailureBecauseStateConflict() throws Exception {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"禁用"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/disable";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"隐藏"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/show";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/" + id + "/status/show";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetDisplayByIdFailureBecauseStateConflict() throws Exception {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"显示"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/show";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 1L; // 此数据由SQL脚本插入，且处于"显示"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/hide";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/" + id + "/status/hide";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testSetHiddenByIdFailureBecauseStateConflict() throws Exception {
        // 测试数据
        Long id = 10L; // 此数据由SQL脚本插入，且处于"隐藏"状态
        // 请求路径
        String url = "/pms/categories/" + id + "/status/hide";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 1L;
        String name = "智能电器";
        String keywords = "智能电器,智能家电";
        String icon = "http://www.tedu.cn/logo.png";
        String sort = "99";
        // 请求路径
        String url = "/pms/categories/" + id + "/base-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("keywords", keywords)
                .param("icon", icon)
                .param("sort", sort)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        String name = "智能电器";
        // 请求路径
        String url = "/pms/categories/" + id + "/base-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateBaseInfoByIdFailureBecauseNameConflict() throws Exception {
        // 测试数据
        Long id = 1L;
        String name = "电脑"; // 此名称已存在于其它记录中，由SQL脚本插入
        // 请求路径
        String url = "/pms/categories/" + id + "/base-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 1L;
        String name = "智能电器";
        String keywords = "智能电器,智能家电";
        String icon = "http://www.tedu.cn/logo.png";
        String enable = "1";
        String isDisplay = "1";
        String sort = "99";
        // 请求路径
        String url = "/pms/categories/" + id + "/base-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("keywords", keywords)
                .param("icon", icon)
                .param("enable", enable)
                .param("isDisplay", isDisplay)
                .param("sort", sort)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        String name = "智能电器";
        // 请求路径
        String url = "/pms/categories/" + id + "/base-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateFullInfoByIdFailureBecauseNameConflict() throws Exception {
        // 测试数据
        Long id = 1L;
        String name = "电脑"; // 此名称已存在于其它记录中，由SQL脚本插入
        // 请求路径
        String url = "/pms/categories/" + id + "/full-info/update";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.CONFLICT.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testGetByIdSuccessfully() throws Exception {
        // 测试数据
        Long id = 1L;
        // 请求路径
        String url = "/pms/categories/" + id;
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByIdFailureBecauseNotFound() throws Exception {
        // 测试数据
        Long id = DMLConst.RowId.NOT_EXISTS;
        // 请求路径
        String url = "/pms/categories/" + id;
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.NOT_FOUND.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByBrandId() throws Exception {
        // 测试数据
        String brandId = "1"; // 由脚本插入，此id下有数据
        String page = "1";
        String pageSize = "5";
        // 请求路径
        String url = "/pms/categories/list-by-brand";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("brandId", brandId)
                .param("page", page)
                .param("pageSize", pageSize)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testListByParentId() throws Exception {
        // 测试数据
        String parentId = "1"; // 由脚本插入，此id下有数据
        String page = "1";
        String pageSize = "5";
        // 请求路径
        String url = "/pms/categories/list-by-parent";
        // 执行测试
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("parentId", parentId)
                .param("page", page)
                .param("pageSize", pageSize)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value(ResponseCode.OK.getValue()))
                .andDo(MockMvcResultHandlers.print());
        log.debug("测试通过！");
    }

}
