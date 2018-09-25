package com.github.danilopaiva.bank.domain

import java.time.LocalDateTime
import java.util.*

class Transaction(
    val id: Id = Id(),
    val accountId: Account.Id,
    val toAccountId: Account.Id,
    val value: Value,
    val type: Type,
    val status: Status,
    val createdAt: CreatedAt,
    val failReason: FailReason
) {

    class Id(val value: String = UUID.randomUUID().toString())

    class Value(val value: Double)

    enum class Type {
        DEPOSITO,
        SAQUE,
        TRANSFERENCIA
    }

    enum class Status {
        PENDING,
        PROCESSING,
        SUCCESS,
        FAIL
    }

    class CreatedAt(val value: LocalDateTime)

    class FailReason(val value: String)
}