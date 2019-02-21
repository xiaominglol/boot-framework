package com.gemini.admin.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * gemini项目配置
 *
 * @author 小明不读书
 * @Date 2018-11-09
 */
@Component
@ConfigurationProperties(prefix = GeminiProperties.PREFIX)
public class GeminiProperties {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final String PREFIX = "gemini";

    private String pictureUploadPath;

    public String getFileUploadPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (StringUtils.isEmpty(pictureUploadPath)) {
            return System.getProperty("java.io.tmpdir");
        } else {
            //判断有没有结尾符,没有得加上
            if (!pictureUploadPath.endsWith(File.separator)) {
                pictureUploadPath = pictureUploadPath + File.separator;
            }
            //判断目录存不存在,不存在得加上
            File file = new File(pictureUploadPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            return pictureUploadPath;
        }
    }

    public String getPictureUploadPath() {
        return pictureUploadPath;
    }

    public void setPictureUploadPath(String pictureUploadPath) {
        this.pictureUploadPath = pictureUploadPath;
    }
}
