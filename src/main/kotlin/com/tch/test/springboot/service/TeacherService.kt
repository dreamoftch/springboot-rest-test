package com.tch.test.springboot.service

import com.tch.test.springboot.model.two.Teacher

/**
 * Created by higgs on 2017/9/29.
 */
interface TeacherService {

    fun addTeacher(teacher: Teacher)

    fun listTeacher(page: Int, size: Int): List<Teacher>

    /**
     * 测试事务是否生效
     */
    fun testTransaction(teacher: Teacher)

}