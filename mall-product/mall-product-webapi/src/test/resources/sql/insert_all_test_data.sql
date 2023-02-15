insert into pms_album (name, description)
VALUES ('小米手机12的相册', '小米手机12的相册的描述');
insert into pms_album (name, description)
VALUES ('小米手机11的相册', '小米手机11的相册的描述');
insert into pms_album (name, description)
VALUES ('华为手机Mate40的相册', '华为手机Mate40的相册的描述');
insert into pms_album (name, description)
VALUES ('华为手机Mate30的相册', '华为手机Mate30的相册的描述');
insert into pms_album (name, description)
VALUES ('华为手机Mate20的相册', '华为手机Mate20的相册的描述');

insert into pms_attribute (template_id, name, description, type, input_type, value_list, sort,
                           is_allow_customize)
VALUES (1, '颜色', '小米手机12的手机颜色', 0, 0, '黑色,蓝色,绿色,紫色', 9, 0),
       (2, '颜色', '小米手机11的手机颜色', 0, 0, '黑色,蓝色,绿色,紫色', 9, 0),
       (3, '颜色', '华为手机Mate40的手机颜色', 0, 0, '黑色,蓝色,绿色,紫色', 9, 0),
       (4, '颜色', '华为手机Mate30的手机颜色', 0, 0, '黑色,蓝色,绿色,紫色', 9, 0),
       (5, '颜色', '华为手机Mate20的手机颜色', 0, 0, '黑色,蓝色,绿色,紫色', 9, 0),
       (1, '屏幕尺寸', '小米手机12的屏幕尺寸', 1, 0, '6.37', 9, 0);

insert into pms_attribute_template (name, pinyin, keywords)
VALUES ('小米手机12的属性模版', 'xiaomishouji12', '小米,小米手机,小米12');
insert into pms_attribute_template (name, pinyin, keywords)
VALUES ('小米手机11的属性模版', 'xiaomishouji11', '小米,小米手机,小米11');
insert into pms_attribute_template (name, pinyin, keywords)
VALUES ('华为手机Mate40的属性模版', 'huaweishoujimate40', '华为,华为手机,华为mate,华为mate40');
insert into pms_attribute_template (name, pinyin, keywords)
VALUES ('华为手机Mate30的属性模版', 'huaweishoujimate30', '华为,华为手机,华为mate,华为mate30');
insert into pms_attribute_template (name, pinyin, keywords)
VALUES ('华为手机Mate20的属性模版', 'huaweishoujimate20', '华为,华为手机,华为mate,华为mate20');

insert into pms_brand (name, pinyin, keywords)
VALUES ('小米', 'xiaomi', '小米,xiaomi,,红米,手机,电视,智能');
insert into pms_brand (name, pinyin, keywords)
VALUES ('华为', 'huawei', '华为,huawei,mate,手机,路由,智能');
insert into pms_brand (name, pinyin, keywords)
VALUES ('Apple', 'apple', '苹果,mac,apple,iphone,ipad,macbook,imac');
insert into pms_brand (name, pinyin, keywords)
VALUES ('雀巢', 'quecao', '雀巢,quecao,咖啡');
insert into pms_brand (name, pinyin, keywords)
VALUES ('格力', 'geli', '格力,geli,空调,电器');

insert into pms_category (id, name, parent_id, `depth`, is_parent, sort, enable, is_display)
values (1, '手机', 0, 1, 1, 99, 1, 1),
       (2, '电脑', 0, 1, 1, 98, 1, 1),
       (3, '家居', 0, 1, 1, 97, 1, 1),
       (4, '游戏手机', 1, 2, 1, 99, 1, 1),
       (5, '拍照手机', 1, 2, 1, 98, 1, 1),
       (6, '笔记本', 2, 2, 1, 99, 1, 1),
       (7, '台式机', 2, 2, 1, 98, 1, 1),
       (8, '服务器', 2, 2, 1, 97, 1, 1),
       (9, '厨具', 3, 3, 1, 99, 1, 1),
       (10, '家纺', 3, 3, 1, 99, 0, 0);

insert into pms_brand_category (brand_id, category_id)
VALUES (1, 4);
insert into pms_brand_category (brand_id, category_id)
VALUES (1, 5);
insert into pms_brand_category (brand_id, category_id)
VALUES (1, 6);
insert into pms_brand_category (brand_id, category_id)
VALUES (1, 7);
insert into pms_brand_category (brand_id, category_id)
VALUES (1, 8);
insert into pms_brand_category (brand_id, category_id)
VALUES (2, 4);
insert into pms_brand_category (brand_id, category_id)
VALUES (2, 5);
insert into pms_brand_category (brand_id, category_id)
VALUES (2, 6);
insert into pms_brand_category (brand_id, category_id)
VALUES (2, 7);
insert into pms_brand_category (brand_id, category_id)
VALUES (2, 8);
insert into pms_brand_category (brand_id, category_id)
VALUES (3, 4);
insert into pms_brand_category (brand_id, category_id)
VALUES (3, 5);
insert into pms_brand_category (brand_id, category_id)
VALUES (3, 6);
insert into pms_brand_category (brand_id, category_id)
VALUES (3, 7);
insert into pms_brand_category (brand_id, category_id)
VALUES (3, 8);

insert into pms_picture (album_id, url, is_cover, width, height)
values (1, 'http://www.tedu.cn/1.jpg', 0, 500, 300),
       (1, 'http://www.tedu.cn/2.jpg', 0, 500, 300),
       (1, 'http://www.tedu.cn/3.jpg', 0, 500, 300),
       (2, 'http://www.tedu.cn/4.jpg', 0, 500, 300),
       (2, 'http://www.tedu.cn/5.jpg', 0, 500, 300),
       (2, 'http://www.tedu.cn/6.jpg', 0, 500, 300),
       (3, 'http://www.tedu.cn/7.jpg', 0, 500, 300),
       (3, 'http://www.tedu.cn/8.jpg', 0, 500, 300),
       (4, 'http://www.tedu.cn/9.jpg', 0, 500, 300),
       (5, 'http://www.tedu.cn/10.jpg', 0, 500, 300);

insert into pms_spu (id, name, type_number, title, list_price, stock, stock_threshold, unit, brand_id, brand_name,
                     category_id, category_name, album_id, pictures, is_published, is_new_arrival, is_recommend, is_checked)
values (1, '小米手机12', 'MI20121201', '年度爆款小米手机12高端旗舰机', 3899, 10000, 100, '部', 1, '小米', 1, '手机', 1, '{JSON...}', 1, 1, 1, 0),
       (2, '小米手机12 Pro', 'MI20121202', '年度爆款小米手机12 Pro顶级旗舰机', 4399, 10000, 100, '部', 1, '小米', 1, '手机', 1, '{JSON...}', 1, 1, 1, 1),
       (3, '华为手机Mate50', 'M20121201', '年度爆款华为手机Mate50高端旗舰机', 4899, 10000, 100, '部', 2, '华为', 1, '手机', 1, '{JSON...}', 1,
        1, 1, 1),
       (4, '华为手机P50', 'HW20121202', '年度爆款华为手机P50高端旗舰机', 4399, 10000, 100, '部', 2, '华为', 1, '手机', 1, '{JSON...}', 1, 1,
        1, 1),
       (5, 'iPhone 13', 'A20121201', '年度爆款iPhone13高端旗舰机', 6199, 10000, 100, '部', 3, 'Apple', 1, '手机', 1, '{JSON...}', 1,
        1, 1, 1);

insert into pms_spu_detail (spu_id, detail)
values (1, '1号SPU的详情');
insert into pms_spu_detail (spu_id, detail)
values (2, '2号SPU的详情');
insert into pms_spu_detail (spu_id, detail)
values (3, '3号SPU的详情');
insert into pms_spu_detail (spu_id, detail)
values (4, '4号SPU的详情');
insert into pms_spu_detail (spu_id, detail)
values (5, '5号SPU的详情');

insert into pms_sku (id, spu_id, title)
VALUES (1, 1, '小米手机12 黑色 8+128');
insert into pms_sku (id, spu_id, title)
VALUES (2, 1, '小米手机12 蓝色 8+128');
insert into pms_sku (id, spu_id, title)
VALUES (3, 1, '小米手机12 黑色 8+256');
insert into pms_sku (id, spu_id, title)
VALUES (4, 3, '华为手机Mate50 黑色 8+128');
insert into pms_sku (id, spu_id, title)
VALUES (5, 3, '华为手机Mate50 黑色 8+128');

insert into pms_sku_specification (sku_id, attribute_id, attribute_name, attribute_value, unit)
values (1, 1, '颜色', '黑色', null);
insert into pms_sku_specification (sku_id, attribute_id, attribute_name, attribute_value, unit)
values (1, 2, '运行内存', '8', 'GB');
insert into pms_sku_specification (sku_id, attribute_id, attribute_name, attribute_value, unit)
values (1, 3, '存储容量', '128', 'GB');
insert into pms_sku_specification (sku_id, attribute_id, attribute_name, attribute_value, unit)
values (1, 4, '重量', '207', '克');
insert into pms_sku_specification (sku_id, attribute_id, attribute_name, attribute_value, unit)
values (1, 5, '有线快充', '120', '瓦');