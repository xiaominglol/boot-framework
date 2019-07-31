package com.gemini.boot.framework.admin;

import com.gemini.boot.framework.core.CoreApplication;
import com.gemini.boot.framework.core.exception.CloudCoreException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 小明不读书
 * @date 2018-11-04
 */

@ComponentScan("com.gemini")
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) throws CloudCoreException {
        CoreApplication.run(AdminApplication.class, args);
    }
}
