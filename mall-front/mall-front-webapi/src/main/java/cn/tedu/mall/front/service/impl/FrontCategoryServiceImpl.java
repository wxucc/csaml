package cn.tedu.mall.front.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.front.service.IFrontCategoryService;
import cn.tedu.mall.pojo.front.entity.FrontCategoryEntity;
import cn.tedu.mall.pojo.front.vo.FrontCategoryTreeVO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import cn.tedu.mall.product.service.front.IForFrontCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class FrontCategoryServiceImpl implements IFrontCategoryService {

    // front模块要dubbo调用product模块的方法,获取数据库中所有的分类信息
    @DubboReference
    private IForFrontCategoryService dubboCategoryService;

    // 装配操作Redis的对象
    @Autowired
    private RedisTemplate redisTemplate;

    // 在开发时,使用Redis规范:使用Key时这个Key必须是一个常量,避免拼写错误
    public static final String CATEGORY_TREE_KEY="category_tree";

    @Override
    public FrontCategoryTreeVO categoryTree() {
        // 先检查Redis中是否已经保存了三级分类树对象
        if(redisTemplate.hasKey(CATEGORY_TREE_KEY)){
            // redis中如果已经有了这个key直接获取即可
            FrontCategoryTreeVO<FrontCategoryEntity> treeVO=
                    (FrontCategoryTreeVO<FrontCategoryEntity>)
                    redisTemplate.boundValueOps(CATEGORY_TREE_KEY).get();
            // 将从Redis中查询出的对象返回
            return treeVO;
        }
        // Redis中如果没有三级分类树信息,表示本次情况可能是首次访问
        // 就需要从数据库中查询分类对象结合后,构建三级分类树,再保存到Redis中了
        // Dubbo调用查询所有分类对象的方法
        List<CategoryStandardVO> categoryStandardVOs =
                        dubboCategoryService.getCategoryList();
        // CategoryStandardVO是没有children属性的,FrontCategoryEntity是有children属性的
        // 下面编写一个专门的方法,用于构建三级分类树对象
        // 大概思路就是先将CategoryStandardVO类型对象转换为FrontCategoryEntity
        // 然后在进行正确的父子关联
        // 整个转换的过程比较复杂,所以我们单独编写一个方法
        FrontCategoryTreeVO<FrontCategoryEntity> treeVO=
                                        initTree(categoryStandardVOs);
        // 上面方法完成了三级分类树的构建,返回包含树结构的treeVO对象
        // 下面要将这个对象保存在Redis中,在后面的请求中直接从Redis中获取treeVO提高效率
        redisTemplate.boundValueOps(CATEGORY_TREE_KEY)
                .set(treeVO,1, TimeUnit.MINUTES);
        // 上面的方法定义了保存到Redis数据的内容和有效期
        // 我们代码中建议定义较小的有效期,例如1分钟,在上线的项目中保存时间会长,例如24小时甚至更长
        // 别忘了最后也返回treeVO
        return treeVO;
    }

    private FrontCategoryTreeVO<FrontCategoryEntity>
                    initTree(List<CategoryStandardVO> categoryStandardVOs) {
        // 第一步:
        // 确定所有分类id包含的子分类对象
        // 我们可以以分类id作为Key,这个分类对象包含的所有子分类作为Value,保存到Map中
        // 因为一个分类对象可以包含多个子分类,所以这个Map的value是List类型
        Map<Long,List<FrontCategoryEntity>> map=new HashMap<>();
        log.info("准备构建三级分类树,节点数量为:{}",categoryStandardVOs.size());
        // 遍历当前方法参数,也就是数据库查询出的所有分类对象集合
        for(CategoryStandardVO categoryStandardVO : categoryStandardVOs){
            // 需要将categoryStandardVO对象中同名属性赋值给FrontCategoryEntity对象
            // 因为需要FrontCategoryEntity类型对象中的childrens属性才能实现父子的关联
            FrontCategoryEntity frontCategoryEntity=new FrontCategoryEntity();
            BeanUtils.copyProperties(categoryStandardVO,frontCategoryEntity);
            // 获取当前对象的父分类id值,以备后续使用
            Long parentId=frontCategoryEntity.getParentId();
            // 判断这个父分类id值,是否已经在map中有对应的Key
            if(!map.containsKey(parentId)){
                // 如果当前map中不包含当前分类对象的父分类id
                // 那么就要新建这个元素,就要确定key和value
                // key就是parentId的值,value是个List对象,list中保存当前分类对象(有childrens的)
                List<FrontCategoryEntity> value=new ArrayList<>();
                value.add(frontCategoryEntity);
                // 在map中添加元素
                map.put(parentId,value);
            }else{
                // 如果map中已经包含当前遍历对象父分类id的Key
                // 那么久就将当前分类对象追加到这个元素的value集合中
                map.get(parentId).add(frontCategoryEntity);
            }
        }
        // 第二步:
        // 将子分类对象添加到对应的分类对象的children属性中
        // 先从获取一级分类对象开始,我们项目设定父分类id为0的是一级分类(Long类型要写0L)
        List<FrontCategoryEntity> firstLevels = map.get(0L);
        // 判断所有一级分类集合如果为null(没有一级分类),直接抛出异常终止程序
        if(firstLevels==null || firstLevels.isEmpty()){
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,"没有一级分类对象!");
        }
        // 遍历一级分类集合
        for(FrontCategoryEntity oneLevel : firstLevels){
            // 一级分类对象的Id就是二级分类对象的父id
            Long secondLevelParentId=oneLevel.getId();// getId()!!!!!!!!!!
            // 根据上面二级分类的父Id,获得这个一级分类包含的所有二级分类对象集合
            List<FrontCategoryEntity> secondLevels = map.get(secondLevelParentId);
            // 判断二级分类对象中是否有元素
            if(secondLevels==null || secondLevels.isEmpty()){
                // 二级分类缺失不抛异常,日志输出警告即可
                log.warn("当前分类没有二级分类内容:{}",secondLevelParentId);
                // 如果二级分类对象缺失,可以直接跳过本次循环剩余的内容,直接开始下次循环
                continue;
            }
            // 确定二级分类对象非空后,开始遍历二级分类对象集合
            for(FrontCategoryEntity twoLevel : secondLevels){
                // 获取二级分类的id,作为三级分类的父id
                Long thirdLevelParentId=twoLevel.getId();// getId()!!!!!!!!!!
                // 根据这个二级分类的父id,获取它对应的所有三级分类的集合
                List<FrontCategoryEntity> thirdLevels=map.get(thirdLevelParentId);
                // 判断thirdLevels是否为null
                if(thirdLevels==null || thirdLevels.isEmpty()){
                    log.warn("当前二级分类没有三级分类内容:{}",thirdLevelParentId);
                    continue;
                }
                // 将三级分类对象集合添加到二级分类对象的childrens属性中
                twoLevel.setChildrens(thirdLevels);
            }
            // 将二级分类对象集合添加到一级分类对象的childrens属性中
            oneLevel.setChildrens(secondLevels);
        }
        // 到此为止,所有的分类对象都应该确认了自己和父\子分类对象的关联关系
        // 最后我们要将firstLevels集合赋给FrontCategoryTreeVO类型对象
        // 实例化这个类型对象,给它的list属性赋值并返回即可
        FrontCategoryTreeVO<FrontCategoryEntity> treeVO=
                new FrontCategoryTreeVO<>();
        treeVO.setCategories(firstLevels);
        // 千万别忘了返回!!!!
        return treeVO;
    }
}
