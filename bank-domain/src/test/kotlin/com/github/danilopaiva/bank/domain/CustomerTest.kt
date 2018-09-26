package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.helper.dummyCustomer
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class CustomerTest {

    private val repository: CustomerRepository = mock()

    @Test
    fun `verify if the repository was called to save a customer`() {
        val customer = dummyCustomer()
        customer.create(repository)
        verify(repository, times(1)).save(customer)
    }

    @Test
    fun `verify if the repository was called to update a customer`() {
        val customer = dummyCustomer()
        customer.update(repository)
        verify(repository, times(1)).update(customer)
    }

    @Test
    fun `verify if the repository was called to delete a customer`() {
        val customer = dummyCustomer()
        customer.delete(repository)
        verify(repository, times(1)).delete(customer.id)
    }
}