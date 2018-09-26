package com.github.danilopaiva.bank.command

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer


class CreateAccount(
    val customerId: Customer.Id,
    val type: Account.Type
)

class UpdateAccount(
    val id: Account.Id,
    val status: Account.Status
)