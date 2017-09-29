package com.tch.test.springboot

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.context.annotation.Bean
import javax.sql.DataSource


/**
 * Created by higgs on 2017/9/28.
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.tch.test.springboot.mapper")
open class Application {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun testDataSource(): DataSource {
        return DruidDataSourceBuilder
                .create()
                .build()
    }

    @Bean
    open fun testTxManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(testDataSource())
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}