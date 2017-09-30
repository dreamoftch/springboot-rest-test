package com.tch.test.springboot.datasource

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

/**
 * Created by higgs on 2017/9/29.
 */
@Configuration
@MapperScan(value = "com.tch.test.springboot.mapper.three", sqlSessionFactoryRef = Test3DatasourceConfig.FACTORY_NAME)
open class Test3DatasourceConfig {

    companion object {
        const val FACTORY_NAME = "test3SqlSessionFactory"
        const val TRANSACTION_MANAGER = "test3TxManager"
        val MAPPER_LOCATION = "classpath:mbg/com/tch/test/springboot/mapper/three/*.xml"
    }

    @Bean(name = arrayOf("test3DataSource"))
    @ConfigurationProperties(prefix = "spring.datasource.druid.three")
    open fun test3DataSource(): DataSource {
        return DruidDataSourceBuilder
                .create()
                .build()
    }

    @Bean(name = arrayOf(TRANSACTION_MANAGER))
    open fun test3TxManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(test3DataSource())
    }

    @Bean(name = arrayOf(Test3DatasourceConfig.FACTORY_NAME))
    open fun test3SqlSessionFactory(): SqlSessionFactory {
//        方式一:不指定mapperLocations,这时候需要mapper xml的路径和mapper接口的package路径一致,否则不能绑定xml和mapper接口
//        val sessionFactory = SqlSessionFactoryBean()
//        sessionFactory.setDataSource(test2DataSource())
//        return sessionFactory.`object`

//        方式二:指定mapperLocations,xml和mapper接口的路径不需要一致,更加灵活
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(test3DataSource())
        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION))
        return sessionFactory.`object`
    }

}