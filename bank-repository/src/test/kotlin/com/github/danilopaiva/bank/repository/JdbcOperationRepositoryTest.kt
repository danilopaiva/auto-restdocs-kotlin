package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.Operation
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class JdbcOperationRepositoryTest : RepositoryBaseTest() {

    private lateinit var customerId: Customer.Id
    private lateinit var accountId: Account.Id

    @Before
    fun setup() {
        customerId = saveACustomer()
        accountId = saveAnAccount(customerId)
    }

    @Test
    fun `should create an operation`() {
        val operation = Operation(
            accountId = accountId,
            type = Operation.Type.DEPOSIT,
            value = Operation.Value(200.0)
        )

        assertEquals(1, operationRepository.save(operation))
    }
}