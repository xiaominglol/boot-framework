package com.gemini.boot.framework.common.mybatis.config;//package com.gemini.cloud.framework.redis.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Druid监控配置
// *
// * @author 小明不读书
// * @date 2018-07-17
// */
//@Configuration
//public class DruidConfig {
//
//    /**
//     * 配置druid的数据源连接池
//     *
//     * @return
//     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 配置Druid的监控
//     * 1、配置一个管理后台的Servlet
//     *
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        Map<String, String> initParams = new HashMap<>();
//        // 账号
//        initParams.put("loginUsername", "admin");
//        // 密码
//        initParams.put("loginPassword", "123456");
//        // IP白名单，默认就是允许所有访问
//        initParams.put("allow", "");
//        // IP黑名单
//        initParams.put("deny", "192.168.15.21");
//        // 能否重置数据
//        initParams.put("resetEnable", "false");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//
//    /**
//     * 2、配置一个web监控的filter
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean webStatFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//        Map<String, String> initParams = new HashMap<>();
//        // 忽略格式
//        initParams.put("exclusions", "*.js,*.css,*.jpg,*.gif,*.png,*.ico,/druid/*");
//        bean.setInitParameters(initParams);
//        // 添加过滤规则
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        return bean;
//    }
//}
