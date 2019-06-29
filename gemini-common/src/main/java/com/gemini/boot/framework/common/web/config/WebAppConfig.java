package com.gemini.boot.framework.common.web.config;//package com.gemini.common.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.MultipartConfigElement;
//
//@Configuration
//public class WebAppConfig {
//
//    /**
//     * 在配置文件中配置的文件保存路径
//     */
//    @Value("${gemini.pictureUploadPath}")
//    private String location;
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大KB,MB
//        factory.setMaxFileSize("2MB");
//        //设置总上传数据总大小
//        factory.setMaxRequestSize("10MB");
//        return factory.createMultipartConfig();
//    }
//}