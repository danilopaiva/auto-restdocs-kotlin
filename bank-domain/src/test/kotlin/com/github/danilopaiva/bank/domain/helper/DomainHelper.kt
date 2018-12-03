package com.github.danilopaiva.bank.domain.helper

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.Operation
import java.util.*

fun dummyAccount(
    customerId: Customer.Id = Customer.Id(),
    type: Account.Type = Account.Type.CURRENT
) =
    Account(
        customerId = customerId,
        type = type
    )

fun dummyCustomer() =
    Customer(
        name = Customer.Name("Danilo Paiva"),
        email = Customer.Email("danilopaivasilva@gmail.com"),
        document = Customer.Document(
            type = Customer.Document.Type.DRIVER_LICENSE,
            number = Customer.Document.Number("123456")
        )
    )

fun dummyOperation() =
    Operation(
        accountId = Account.Id(),
        type = Operation.Type.DEPOSIT,
        value = Operation.Value(100.0)
    )

fun randomUUID() =
    UUID.randomUUID().toString()