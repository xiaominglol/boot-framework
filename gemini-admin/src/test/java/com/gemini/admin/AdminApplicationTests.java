//package com.gemini.base;
//
//import com.gemini.base.sys.service.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
////import org.springframework.data.redis.core.RedisTemplate;
////import org.springframework.data.redis.core.StringRedisTemplate;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CoreApplicationTests {
//    @Autowired
//    OrgService orgService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    MenuService menuService;
//
//    @Autowired
//    DictService dictService;
//
//    @Autowired
//    UserService userService;
//
////    @Autowired
////    StringRedisTemplate stringRedisTemplate;
////
////    @Autowired
////    RedisTemplate redisTemplate;
////
////    @Autowired
////    RedisTemplate<Object, Object> objectRedisTemplate;
//
//    /**
//     * Redis常见的五大数据类型
//     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
//     *  stringRedisTemplate.opsForValue()[String（字符串）]
//     *  stringRedisTemplate.opsForList()[List（列表）]
//     *  stringRedisTemplate.opsForSet()[Set（集合）]
//     *  stringRedisTemplate.opsForHash()[Hash（散列）]
//     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
//     */
//    @Test
//    public void test1(){
////        stringRedisTemplate.opsForValue().append("a","a");
////
////        stringRedisTemplate.opsForList().leftPush("mylist","1");
////		stringRedisTemplate.opsForList().leftPush("mylist","2");
//    }
//
//    @Test
//    public void contextLoads() {
////        Org org = orgService.getById(87);
////        Menu menu = menuService.getById(1);
////        User user = userService.getById("admin");
////        Dict dict = dictService.getById(22);
////        Role role = roleService.getById(1);
////        objectRedisTemplate.opsForValue().set("org",org);
////        objectRedisTemplate.opsForValue().set("menu",menu);
////        objectRedisTemplate.opsForValue().set("user",user);
////        objectRedisTemplate.opsForValue().set("dict",dict);
////        objectRedisTemplate.opsForValue().set("role",role);
//    }
//
//    @Test
//    public void testFile() throws FileNotFoundException {
//        //获取跟目录
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//
//        System.out.println("path111111111:"+path.getAbsolutePath());
//        if(!path.exists()) path = new File("");
//        System.out.println("path:"+path.getAbsolutePath());
//
////如果上传目录为/static/images/upload/，则可以如下获取：
//        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
//        if(!upload.exists()) upload.mkdirs();
//        System.out.println("upload url:"+upload.getAbsolutePath());
////在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
////在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
//    }
//}
