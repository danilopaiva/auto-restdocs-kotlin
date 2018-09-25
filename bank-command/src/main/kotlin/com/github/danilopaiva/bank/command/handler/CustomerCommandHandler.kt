package com.github.danilopaiva.bank.command.handler

import com.github.danilopaiva.bank.command.CreateCustomer
import com.github.danilopaiva.bank.command.DeleteCustomer
import com.github.danilopaiva.bank.command.UpdateCustomer
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
open class CustomerCommandHandler(private val repository: CustomerRepository) {

    fun handler(command: CreateCustomer): Customer {
        val customer = Customer(
            name = command.name,
            document = command.document,
            email = command.email
        )

        return customer.create(repository)
    }

    fun handler(command: UpdateCustomer): Customer {
        val customer = find(command.id)
        val customerToUpdate = Customer(
            id = customer.id,
            name = command.name,
            email = command.email,
            document = command.document
        )
        return customerToUpdate.update(repository)
    }

    fun handler(command: DeleteCustomer) {
        val customer = find(command.id)
        customer.delete(repository)
    }

    fun find(id: Customer.Id): Customer {
        return Optional.ofNullable(repository.find(id))
            .orElseThrow {
                Exception()
            }
    }
}