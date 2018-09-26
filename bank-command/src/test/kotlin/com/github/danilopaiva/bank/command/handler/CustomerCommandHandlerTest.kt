package com.github.danilopaiva.bank.command.handler

import com.github.danilopaiva.bank.command.CreateCustomer
import com.github.danilopaiva.bank.command.DeleteCustomer
import com.github.danilopaiva.bank.command.UpdateCustomer
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.helper.randomUUID
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class CustomerCommandHandlerTest {

    private val repository: CustomerRepository = mock()
    private lateinit var commandHandler: CustomerCommandHandler

    @Before
    fun setup() {
        commandHandler = CustomerCommandHandler(repository)
    }

    @Test
    fun `verify if the repository was called to save a customer`() {
        val command = dummyCreateCustomer()
        commandHandler.handler(command)
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify if the repository was called to update a customer`() {
        val customer = dummyCustomer()
        whenever(repository.find(customer.id)).thenReturn(customer)

        val command = dummyUpdateCustomer(customer)
        commandHandler.handler(command)
        verify(repository, times(1)).update(any())
    }

    @Test
    fun `verify if the repository was called to delete a customer`() {
        val customer = dummyCustomer()
        whenever(repository.find(customer.id)).thenReturn(customer)

        commandHandler.handler(DeleteCustomer(customer.id))
        verify(repository, times(1)).delete(customer.id)
    }

    private fun dummyCreateCustomer() =
        CreateCustomer(
            name = Customer.Name("A A"),
            email = Customer.Email("a@a.com"),
            document = Customer.Document(
                type = Customer.Document.Type.IDENTITY,
                number = Customer.Document.Number(randomUUID())
            )
        )

    private fun dummyUpdateCustomer(customer: Customer) =
        UpdateCustomer(
            id = customer.id,
            name = customer.name,
            email = customer.email,
            document = customer.document
        )
}