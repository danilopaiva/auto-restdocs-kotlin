package com.github.danilopaiva.bank.web.helper

import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateAccountRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.request.ValueRequest
import com.github.danilopaiva.bank.command.CreateAccount
import com.github.danilopaiva.bank.command.CreateCustomer
import com.github.danilopaiva.bank.command.UpdateAccount
import com.github.danilopaiva.bank.command.UpdateCustomer
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.Transaction

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

fun CreateAccountRequest.toCommand() =
    CreateAccount(
        customerId = Customer.Id(this.customerId!!),
        type = enumValueOf(this.type!!)
    )

fun UpdateAccountRequest.toCommand(id: Account.Id) =
    UpdateAccount(
        id = id,
        status = enumValueOf(this.status!!)
    )

fun ValueRequest.toDepositCommand(id: Transaction.Id) =
    DoDeposit(
        id = id,
        value = this.value
    )