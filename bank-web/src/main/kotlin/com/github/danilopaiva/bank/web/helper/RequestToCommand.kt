package com.github.danilopaiva.bank.web.helper

import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.command.CreateCustomer
import com.github.danilopaiva.bank.command.UpdateCustomer
import com.github.danilopaiva.bank.domain.Customer

fun CreateCustomerRequest.toCommand() =
    CreateCustomer(
        name = Customer.Name(this.name!!),
        document = Customer.Document(
            type = Customer.Document.Type.valueOf(this.document!!.type!!),
            number = Customer.Document.Number(this.document!!.number!!)
        ),
        email = Customer.Email(this.email!!)
    )

fun UpdateCustomerRequest.toCommand(id: Customer.Id) =
    UpdateCustomer(
        id = id,
        name = Customer.Name(this.name!!),
        document = Customer.Document(
            type = Customer.Document.Type.valueOf(this.document!!.type!!),
            number = Customer.Document.Number(this.document!!.number!!)
        ),
        email = Customer.Email(this.email!!)
    )