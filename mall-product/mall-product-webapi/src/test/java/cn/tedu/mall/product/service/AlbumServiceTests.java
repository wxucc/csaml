package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.AlbumAddNewDTO;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;
import cn.tedu.mall.product.SqlScript;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Slf4j
public class AlbumServiceTests {

    @Autowired
    IAlbumService service;

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE})
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewSuccessfully() {
        assertDoesNotThrow(() -> {
            AlbumAddNewDTO albumAddnewDTO = new AlbumAddNewDTO();
            albumAddnewDTO.setName("小米手机12的相册");
            albumAddnewDTO.setDescription("小米手机12的相册的描述，暂无内容");
            albumAddnewDTO.setSort(0);
            service.addNew(albumAddnewDTO);
        });
    }

    @Test
    @Sql(scripts = {SqlScript.TRUNCATE_ALL_TABLE, SqlScript.INSERT_ALL_TEST_DATA})
    void testList() {
        Integer page = 1;
        Integer pageSize = 2;
        JsonPage<AlbumStandardVO> jsonPage = service.list(page, pageSize);
        log.debug("记录数：{}", jsonPage.getList().size());
        for (AlbumStandardVO item : jsonPage.getList()) {
            log.debug("{}", item);
        }
    }

}
