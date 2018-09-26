package com.github.danilopaiva.bank.domain.helper

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer

fun dummyAccount(
    customerId: Customer.Id = Customer.Id(),
    type: Account.Type = Account.Type.CURRENT
) =
    Account(
        customerId = customerId,
        type = type
    )