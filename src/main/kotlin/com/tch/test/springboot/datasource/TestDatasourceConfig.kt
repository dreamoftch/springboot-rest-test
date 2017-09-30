package com.tch.test.springboot.datasource

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


/**
 * Created by higgs on 2017/9/29.
 */
@Configuration
@MapperScan(value = "com.tch.test.springboot.mapper.one", sqlSessionFactoryRef = TestDatasourceConfig.factoryName)
open class TestDatasourceConfig {

    companion object {
        const val factoryName = "testSqlSessionFactory"
    }

    @Primary //当有多个数据源的时候,Primary标记的数据源为主数据源
    @Bean(name = arrayOf("testDataSource"))
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    open fun testDataSource(): DataSource {
        return DruidDataSourceBuilder
                .create()
                .build()
    }

    @Bean(name = arrayOf("testTxManager"))
    open fun testTxManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(testDataSource())
    }

    @Bean(name = arrayOf(TestDatasourceConfig.factoryName))
    open fun testSqlSessionFactory(): SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(testDataSource())
        return sessionFactory.`object`
    }

}