package cn.tedu.mall.order.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListConvertUtils {

    public static <T> List<T> stringToList(String json, Class<T> object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, object);
            List<T> list = objectMapper.readValue(json, listType);
            return list;
        } catch (JsonProcessingException e) {
            log.info("将当前类:{}转化list时失败",object.getName());
            e.printStackTrace();
        }
        return null;
    }

}
