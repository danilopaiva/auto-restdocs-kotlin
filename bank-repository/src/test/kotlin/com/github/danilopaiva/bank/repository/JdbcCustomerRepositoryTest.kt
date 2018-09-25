package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class JdbcCustomerRepositoryTest : RepositoryBaseTest() {

    @Autowired
    lateinit var repository: CustomerRepository

    @Test
    fun `should save a customer`() {
        val customer = dummyCustomer()
        assertEquals(1, repository.save(customer))
    }
}