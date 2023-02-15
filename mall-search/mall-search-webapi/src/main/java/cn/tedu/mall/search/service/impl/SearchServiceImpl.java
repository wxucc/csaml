package cn.tedu.mall.search.service.impl;


//@Service
//@Slf4j
//public class SearchServiceImpl implements ISearchService {
//
//    // dubbo调用product模块查询所有spu的方法
//    @DubboReference
//    private IForFrontSpuService dubboSpuService;
//    // 操作ES的接口
//    @Autowired
//    private SpuForElasticRepository spuRepository;
//
//    @Override
//    public void loadSpuByPage() {
//        // 这个方法需要循环调用分页查询spu数据的方法
//        // 每次循环查询出一页数据,然后新增到ES中
//        // 直到把所有数据新增到ES中为止,但是我们要确定总页数,才能知道循环次数
//        // 我们需要先运行一次查询,获取返回的JsonPage对象,才知道总页数,所以是先执行后判断,推荐do-while循环
//        int i=1; // 循环变量,从1开始,可以直接当做页码使用
//        int page; // 总页数,也是循环次数条件,先声明,后面循环中赋值
//        //do-while循环结构
//        do{
//            // dubbo调用查询spu表中数据
//            JsonPage<Spu> spus=dubboSpuService.getSpuByPage(i,2);
//            // 实例化一个集合,泛型类型是SpuForElastic
//            List<SpuForElastic> esSpus=new ArrayList<>();
//            // 遍历查询出的spus集合,把其中的元素转换为SpuForElastic类型添加到esSpus
//            for(Spu spu : spus.getList()){
//                SpuForElastic esSpu=new SpuForElastic();
//                BeanUtils.copyProperties(spu,esSpu);
//                // 添加到esSpus集合中
//                esSpus.add(esSpu);
//            }
//            // esSpus集合中包含了本页的数据,下面新增到Es中
//            spuRepository.saveAll(esSpus);
//            log.info("成功加载了第{}页数据",i);
//            // 为下次循环做准备
//            i++;
//            // 给page赋值
//            page=spus.getTotalPage();
//
//        }while(i<=page);
//    }
//
//    // 根据用户输入的关键字进行分页搜索的方法
//    @Override
//    public JsonPage<SpuForElastic> search(String keyword, Integer page, Integer pageSize) {
//        // 根据参数中的分页数据进行分页查询,注意SpringData=页码从0开始
//        Page<SpuForElastic> spus=spuRepository.querySearch(
//                                keyword, PageRequest.of(page-1,pageSize));
//        // 分页查询调用结束,返回Page对象,我们要转换为JsonPage对象返回
//        JsonPage<SpuForElastic> jsonPage=new JsonPage<>();
//        // 赋值相关数据
//        jsonPage.setPage(page);
//        jsonPage.setPageSize(pageSize);
//        jsonPage.setTotalPage(spus.getTotalPages());
//        jsonPage.setTotal(spus.getTotalElements());
//        jsonPage.setList(spus.getContent());
//        // 最后返回!!!
//        return jsonPage;
//    }
//
//
//}
