package com.tch.test.springboot.service.impl

import com.tch.test.springboot.datasource.Test3DatasourceConfig
import com.tch.test.springboot.mapper.three.OrganizationMapper
import com.tch.test.springboot.model.three.Organization
import com.tch.test.springboot.model.three.OrganizationExample
import com.tch.test.springboot.service.OrganizationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by higgs on 2017/9/29.
 */
@Service
open class OrganizationServiceImpl: OrganizationService {

    private val LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl::class.java)

    @Autowired
    private lateinit var organizationMapper: OrganizationMapper

    override fun addOrganization(organization: Organization) {
        organizationMapper.insertSelective(organization)
    }

    override fun listOrganization(page: Int, size: Int): List<Organization> {
        return organizationMapper.selectByExample(OrganizationExample())
    }

    @Transactional(transactionManager = Test3DatasourceConfig.TRANSACTION_MANAGER)
    override fun testTransaction(organization: Organization) {
        //测试事务是否起作用
        val flag = true
        organizationMapper.insert(organization)
        if (flag) {
            throw RuntimeException("organization操作异常。。。")
        }
        organizationMapper.insert(organization)
    }
}