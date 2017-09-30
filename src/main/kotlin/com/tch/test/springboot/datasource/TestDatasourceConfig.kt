package com.tch.test.springboot.datasource

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


/**
 * Created by higgs on 2017/9/29.
 */
@Configuration
@MapperScan(value = "com.tch.test.springboot.mapper.one", sqlSessionFactoryRef = TestDatasourceConfig.FACTORY_NAME)
open class TestDatasourceConfig {

    companion object {
        const val FACTORY_NAME = "testSqlSessionFactory"
        const val MAPPER_LOCATION = "classpath:mbg/com/tch/test/springboot/mapper/one/*.xml"
    }

    //当有多个数据源的时候,Primary标记的数据源为主数据源,如果不加该标记，在多数据情况下就不知道该选哪个数据源了
    @Primary
    @Bean(name = arrayOf("testDataSource"))
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    open fun testDataSource(): DataSource {
        return DruidDataSourceBuilder
                .create()
                .build()
    }

    @Primary
    @Bean(name = arrayOf("testTxManager"))
    open fun testTxManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(testDataSource())
    }

    @Primary
    @Bean(name = arrayOf(TestDatasourceConfig.FACTORY_NAME))
    open fun testSqlSessionFactory(): SqlSessionFactory {
//        方式一:不指定mapperLocations,这时候需要mapper xml的路径和mapper接口的package路径一致,否则不能绑定xml和mapper接口
//        val sessionFactory = SqlSessionFactoryBean()
//        sessionFactory.setDataSource(test2DataSource())
//        return sessionFactory.`object`

//        方式二:指定mapperLocations,xml和mapper接口的路径不需要一致,更加灵活
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(testDataSource())
        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver()
                .getResources(TestDatasourceConfig.MAPPER_LOCATION))
        return sessionFactory.`object`
    }

}