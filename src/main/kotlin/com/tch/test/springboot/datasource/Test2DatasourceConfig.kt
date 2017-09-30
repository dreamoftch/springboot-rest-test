package com.tch.test.springboot.datasource

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

/**
 * Created by higgs on 2017/9/29.
 */
@Configuration
@MapperScan(value = "com.tch.test.springboot.mapper.two", sqlSessionFactoryRef = Test2DatasourceConfig.factoryName)
open class Test2DatasourceConfig {

    companion object {
        const val factoryName = "test2SqlSessionFactory"
    }

    @Qualifier("test2")
    @Bean(name = arrayOf("test2DataSource"))
    @ConfigurationProperties(prefix = "spring.datasource.druid.two")
    open fun test2DataSource(): DataSource {
        return DruidDataSourceBuilder
                .create()
                .build()
    }

    @Bean(name = arrayOf("test2TxManager"))
    open fun test2TxManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(test2DataSource())
    }

    @Bean(name = arrayOf(Test2DatasourceConfig.factoryName))
    open fun test2SqlSessionFactory(): SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(test2DataSource())
        return sessionFactory.`object`
    }

}