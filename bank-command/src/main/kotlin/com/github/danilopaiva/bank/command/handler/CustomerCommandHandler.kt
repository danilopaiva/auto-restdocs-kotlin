package com.github.danilopaiva.bank.command.handler

import com.github.danilopaiva.bank.command.CreateCustomer
import com.github.danilopaiva.bank.domain.Customer
import org.springframework.stereotype.Component

@Component
open class CustomerCommandHandler {

    fun handler(command: CreateCustomer): Customer {
        val customer = Customer(
            name = command.name,
            document = command.document,
            email = command.email
        )

        return customer.create()
    }
}