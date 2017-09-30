package com.tch.test.springboot

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * Created by higgs on 2017/9/28.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = arrayOf(MybatisAutoConfiguration::class))
@EnableTransactionManagement
open class Application {

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}