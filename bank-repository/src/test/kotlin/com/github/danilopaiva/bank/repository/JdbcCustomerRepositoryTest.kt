package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class JdbcCustomerRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save a customer`() {
        saveACustomer()
    }

    @Test
    fun `should find a customer already created`() {
        val customerId = saveACustomer()
        val customerFound = customerRepository.find(customerId)
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

        assertEquals(1, customerRepository.update(customerToUpdate))

        val customerFound = customerRepository.find(customer.id)
        assertNotNull(customerFound)
        assertNotEquals(customer.name, customerFound?.name)
        assertNotEquals(customer.email, customerFound?.email)
        assertNotEquals(customer.document, customerFound?.document)
    }

    @Test
    fun `should delete a customer already created`() {
        val customerId = saveACustomer()
        assertEquals(1, customerRepository.delete(customerId))

        val customerFound = customerRepository.find(customerId)
        assertNull(customerFound)
    }
}