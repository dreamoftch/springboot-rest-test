package com.tch.test.springboot.controller

import com.tch.test.springboot.model.Student
import com.tch.test.springboot.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by higgs on 2017/9/29.
 */
@RestController
@RequestMapping(("/student"))
class StudentController {

    @Autowired
    private lateinit var studentService: StudentService

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addStudent(@RequestBody student: Student): Student {
        studentService.addStudent(student)
        return student
    }

    @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
    fun listStudent(): List<Student> {
        return studentService.listStudent(1, 10)
    }

    @RequestMapping(value = "/testTransaction", method = arrayOf(RequestMethod.POST))
    fun testTransaction(@RequestBody student: Student): Student {
        studentService.testTransaction(student)
        return student
    }

}