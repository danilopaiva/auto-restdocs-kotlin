package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class JdbcCustomerRepositoryTest : RepositoryBaseTest() {

    @Autowired
    lateinit var repository: CustomerRepository

    @Test
    fun `should save a customer`() {
        saveACustomer()
    }

    private fun saveACustomer(customer: Customer = dummyCustomer()): Customer.Id {
        assertEquals(1, repository.save(customer))
        return customer.id
    }

    @Test
    fun `should find a customer already created`() {
        val customerId = saveACustomer()
        val customerFound = repository.find(customerId)
        assertNotNull(customerFound)
        assertEquals(customerId, customerFound?.id)
    }

    @Test
    fun `should update a customer already created`() {
        val customer = dummyCustomer()
        saveACustomer(customer)
        val customerToUpdate = customer.copy(
            name = Customer.Name("opa"),
            email = Customer.Email("a@a.com"),
            document = Customer.Document(Customer.Document.Type.IDENTITY, Customer.Document.Number("321"))
        )

        assertEquals(1, repository.update(customerToUpdate))

        val customerFound = repository.find(customer.id)
        assertNotNull(customerFound)
        assertNotEquals(customer.name, customerFound?.name)
        assertNotEquals(customer.email, customerFound?.email)
        assertNotEquals(customer.document, customerFound?.document)
    }

    @Test
    fun `should delete a customer already created`() {
        val customerId = saveACustomer()
        assertEquals(1, repository.delete(customerId))

        val customerFound = repository.find(customerId)
        assertNull(customerFound)
    }
}