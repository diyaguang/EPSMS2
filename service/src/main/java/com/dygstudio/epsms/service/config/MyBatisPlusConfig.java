package com.dygstudio.epsms.service.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 〈功能概述〉
 *
 * @className: MyBatisPlusConfig
 * @package: com.dygstudio.epsms.service.config
 * @author: diyaguang
 * @date: 2019-12-30 16:03
 */
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
