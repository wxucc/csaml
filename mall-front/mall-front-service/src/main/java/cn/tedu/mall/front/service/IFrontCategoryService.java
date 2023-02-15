package cn.tedu.mall.front.service;

import cn.tedu.mall.pojo.front.vo.FrontCategoryTreeVO;

/**
 * 商城前台分类接口
 * @author xiaoxuwei
 * @version 1.0.0
 */
public interface IFrontCategoryService {
    /**
     * 首页查询分类树
     * 需要返回固定结构
     */
    FrontCategoryTreeVO categoryTree();
}





