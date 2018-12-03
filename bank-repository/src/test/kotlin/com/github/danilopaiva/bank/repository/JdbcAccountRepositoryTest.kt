package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JdbcAccountRepositoryTest : RepositoryBaseTest() {

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
        val account = accountRepository.find(accountId)
        assertNotNull(account)
        assertEquals(accountId.value, account?.id?.value)
    }

    @Test
    fun `should update the status the account already created`() {
        val status = Account.Status.CLOSED
        assertEquals(1, accountRepository.update(accountId, status))

        val account = accountRepository.find(accountId)
        assertNotNull(account)
        assertEquals(status, account?.status)
    }

    @Test
    fun `should update the amount the account`() {
        val amount = Account.Amount(500.0)
        assertEquals(1, accountRepository.update(accountId, amount))

        val account = accountRepository.find(accountId)
        assertNotNull(account)
        assertEquals(amount.value, account?.amount?.value)
    }
}