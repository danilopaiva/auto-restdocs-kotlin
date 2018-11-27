package com.github.danilopaiva.bank.application.helper

import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer

fun CreateCustomerRequest.toDomain() =
    Customer(
        name = Customer.Name(this.name!!),
        document = Customer.Document(
            type = Customer.Document.Type.valueOf(this.document!!.type!!),
            number = Customer.Document.Number(this.document!!.number!!)
        ),
        email = Customer.Email(this.email!!)
    )

fun UpdateCustomerRequest.toDomain(id: Customer.Id) =
    Customer(
        id = id,
        name = Customer.Name(this.name!!),
        document = Customer.Document(
            type = Customer.Document.Type.valueOf(this.document!!.type!!),
            number = Customer.Document.Number(this.document!!.number!!)
        ),
        email = Customer.Email(this.email!!)
    )

fun CreateAccountRequest.toDomain() =
    Account(
        customerId = Customer.Id(this.customerId!!),
        type = enumValueOf(this.type!!)
    )