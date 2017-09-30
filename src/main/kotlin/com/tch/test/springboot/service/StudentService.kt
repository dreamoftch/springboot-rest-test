package com.tch.test.springboot.service

import com.tch.test.springboot.model.one.Student

/**
 * Created by higgs on 2017/9/29.
 */
interface StudentService {

    fun addStudent(student: Student)

    fun listStudent(page: Int, size: Int): List<Student>

    /**
     * 测试事务是否生效
     */
    fun testTransaction(student: Student)

}