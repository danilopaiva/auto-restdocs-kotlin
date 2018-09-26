package com.github.danilopaiva.bank.web.helper

import com.github.danilopaiva.bank.api.response.AccountResponse
import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer

fun Customer.toResponse() =
    CustomerResponse(
        id = this.id.value,
        name = this.name.value,
        email = this.email.value,
        document = buildDocument(this.document),
        createdAt = this.createdAt.value
    )

private fun buildDocument(document: Customer.Document): CustomerResponse.DocumentResponse =
    CustomerResponse.DocumentResponse(
        type = document.type.name,
        number = document.number.value
    )

//Account
fun Account.toResponse() =
    AccountResponse(
        id = this.id.value,
        customerId = this.customerId.value,
        type = this.type.name,
        status = this.status.name,
        amount = this.amount.value,
        createdAt = this.createdAt.value
    )