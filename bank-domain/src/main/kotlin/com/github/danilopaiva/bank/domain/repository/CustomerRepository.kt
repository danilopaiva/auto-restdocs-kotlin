package com.github.danilopaiva.bank.domain.repository

import com.github.danilopaiva.bank.domain.Customer

interface CustomerRepository {

    fun save(customer: Customer): Int

    fun find(customerId: Customer.Id): Customer?

    fun update(customer: Customer): Int

    fun delete(id: Customer.Id): Int
}