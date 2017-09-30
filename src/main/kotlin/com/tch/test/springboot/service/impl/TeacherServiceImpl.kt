package com.tch.test.springboot.service.impl

import com.tch.test.springboot.mapper.two.TeacherMapper
import com.tch.test.springboot.model.two.Teacher
import com.tch.test.springboot.model.two.TeacherExample
import com.tch.test.springboot.service.TeacherService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by higgs on 2017/9/29.
 */
@Service
open class TeacherServiceImpl: TeacherService {

    private val LOGGER = LoggerFactory.getLogger(StudentServiceImpl::class.java)

    @Autowired
    private lateinit var teacherMapper: TeacherMapper

    override fun addTeacher(teacher: Teacher) {
        teacherMapper.insertSelective(teacher)
    }

    override fun listTeacher(page: Int, size: Int): List<Teacher> {
        return teacherMapper.selectByExample(TeacherExample())
    }


    @Transactional
    override fun testTransaction(teacher: Teacher) {
        //测试事务是否起作用
        val flag = true
        teacherMapper.insert(teacher)
        if (flag) {
            throw RuntimeException("teacher操作异常。。。")
        }
        teacherMapper.insert(teacher)
    }
}