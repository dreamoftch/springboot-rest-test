package com.tch.test.springboot.service.impl

import com.tch.test.springboot.mapper.one.StudentMapper
import com.tch.test.springboot.model.one.Student
import com.tch.test.springboot.model.one.StudentExample
import com.tch.test.springboot.service.StudentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by higgs on 2017/9/29.
 */
@Service
open class StudentServiceImpl: StudentService {

    private val LOGGER = LoggerFactory.getLogger(StudentServiceImpl::class.java)

    @Autowired
    private lateinit var studentMapper: StudentMapper

    override fun addStudent(student: Student) {
        studentMapper.insertSelective(student)
    }

    override fun listStudent(page: Int, size: Int): List<Student> {
        return studentMapper.selectByExample(StudentExample())
    }

    @Transactional
    override fun testTransaction(student: Student) {
        //测试事务是否起作用
        val flag = true
        studentMapper.insert(student)
        if (flag) {
            throw RuntimeException("student操作异常。。。")
        }
        studentMapper.insert(student)
    }
}