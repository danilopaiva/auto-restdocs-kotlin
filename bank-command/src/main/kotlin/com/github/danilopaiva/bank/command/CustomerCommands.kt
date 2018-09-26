package com.github.danilopaiva.bank.command

import com.github.danilopaiva.bank.domain.Customer


class CreateCustomer(
    val name: Customer.Name,
    val document: Customer.Document,
    val email: Customer.Email
)

class UpdateCustomer(
    val id: Customer.Id,
    val name: Customer.Name,
    val document: Customer.Document,
    val email: Customer.Email
)

class DeleteCustomer(val id: Customer.Id)