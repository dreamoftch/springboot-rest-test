package com.tch.test.springboot.service

import com.tch.test.springboot.model.Student

/**
 * Created by higgs on 2017/9/29.
 */
interface StudentService {

    fun addStudent(student: Student)

    fun listStudent(page: Int, size: Int): List<Student>

    fun testTransaction(student: Student)

}