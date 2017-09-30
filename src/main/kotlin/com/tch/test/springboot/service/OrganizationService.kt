package com.tch.test.springboot.service

import com.tch.test.springboot.model.three.Organization

/**
 * Created by higgs on 2017/9/29.
 */
interface OrganizationService {

    fun addOrganization(organization: Organization)

    fun listOrganization(page: Int, size: Int): List<Organization>

    /**
     * 测试事务是否生效
     */
    fun testTransaction(organization: Organization)

}