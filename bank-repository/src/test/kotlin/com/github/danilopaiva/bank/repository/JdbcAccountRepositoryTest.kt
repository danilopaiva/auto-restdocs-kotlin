package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyAccount
import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class JdbcAccountRepositoryTest : RepositoryBaseTest() {

    @Autowired
    private lateinit var repository: AccountRepository

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    private lateinit var customerId: Customer.Id
    private lateinit var accountId: Account.Id

    @Before
    fun setup() {
        customerId = saveACustomer()
        accountId = saveAnAccount(customerId)
    }

    @Test
    fun `should save an account`() {
        saveAnAccount(customerId)
    }

    @Test
    fun `should find a customer already created`() {
        val account = repository.find(accountId)
        assertNotNull(account)
        assertEquals(accountId.value, account?.id?.value)
    }

    @Test
    fun `should update the status the account already created`() {
        val status = Account.Status.CLOSED
        assertEquals(1, repository.update(accountId, status))

        val account = repository.find(accountId)
        assertNotNull(account)
        assertEquals(status, account?.status)
    }

    private fun saveACustomer(customer: Customer = dummyCustomer()): Customer.Id {
        assertEquals(1, customerRepository.save(customer))
        return customer.id
    }

    private fun saveAnAccount(customerId: Customer.Id = Customer.Id()): Account.Id {
        val account = dummyAccount(customerId = customerId)
        assertEquals(1, repository.save(account))
        return account.id
    }
}