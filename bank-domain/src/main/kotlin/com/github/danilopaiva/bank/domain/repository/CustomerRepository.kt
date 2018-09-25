package com.github.danilopaiva.bank.domain.repository

import com.github.danilopaiva.bank.domain.Customer

interface CustomerRepository {

    fun save(customer: Customer): Int
}