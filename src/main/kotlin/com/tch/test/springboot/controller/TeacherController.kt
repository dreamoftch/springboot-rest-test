package com.tch.test.springboot.controller

import com.tch.test.springboot.model.two.Teacher
import com.tch.test.springboot.service.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 用的数据源是test2数据库
 * Created by higgs on 2017/9/29.
 */
@RestController
@RequestMapping(("/teacher"))
class TeacherController {

    @Autowired
    private lateinit var teacherService: TeacherService

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addTeacher(@RequestBody teacher: Teacher): Teacher {
        teacherService.addTeacher(teacher)
        return teacher
    }

    @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
    fun listTeacher(): List<Teacher> {
        return teacherService.listTeacher(1, 10)
    }

    @RequestMapping(value = "/testTransaction", method = arrayOf(RequestMethod.POST))
    fun testTransaction(@RequestBody teacher: Teacher): Teacher {
        teacherService.testTransaction(teacher)
        return teacher
    }

}