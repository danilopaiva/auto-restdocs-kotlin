package com.github.danilopaiva.bank.domain

import java.time.LocalDateTime
import java.util.*

class Operation(
    val id: Id = Id(),
    val accountId: Account.Id,
    val toAccountId: Account.Id? = null,
    val value: Value,
    val status: Status,
    val createdAt: CreatedAt = CreatedAt(LocalDateTime.now())
) {
    lateinit var type: Type
    lateinit var failReason: FailReason

    class Id(val value: String = UUID.randomUUID().toString())

    class Value(val value: Double)

    enum class Type {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
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