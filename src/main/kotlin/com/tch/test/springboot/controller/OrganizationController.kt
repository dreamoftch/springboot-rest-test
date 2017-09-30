package com.tch.test.springboot.controller

import com.tch.test.springboot.model.three.Organization
import com.tch.test.springboot.service.OrganizationService
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
@RequestMapping(("/organization"))
class OrganizationController {

    @Autowired
    private lateinit var organizationService: OrganizationService

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addTeacher(@RequestBody organization: Organization): Organization {
        organizationService.addOrganization(organization)
        return organization
    }

    @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
    fun listTeacher(): List<Organization> {
        return organizationService.listOrganization(1, 10)
    }

    @RequestMapping(value = "/testTransaction", method = arrayOf(RequestMethod.POST))
    fun testTransaction(@RequestBody organization: Organization): Organization {
        organizationService.testTransaction(organization)
        return organization
    }

}