package com.github.danilopaiva.bank.repository.config

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyAccount
import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.github.danilopaiva.bank.domain.repository.OperationRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(RepositoryTestConfig::class)])
abstract class RepositoryBaseTest {

    @Autowired
    protected lateinit var accountRepository: AccountRepository

    @Autowired
    protected lateinit var operationRepository: OperationRepository

    @Autowired
    protected lateinit var customerRepository: CustomerRepository

    protected fun saveACustomer(customer: Customer = dummyCustomer()): Customer.Id {
        assertEquals(1, customerRepository.save(customer))
        return customer.id
    }

    protected fun saveAnAccount(customerId: Customer.Id = Customer.Id()): Account.Id {
        val account = dummyAccount(customerId = customerId)
        assertEquals(1, accountRepository.save(account))
        return account.id
    }
}