package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.repository.OperationRepository
import java.time.LocalDateTime
import java.util.*

class Operation(
    val id: Id = Id(),
    val accountId: Account.Id,
    var type: Type,
    val value: Value,
    val toAccountId: Account.Id? = null,
    val status: Status = Status.PROCESSING,
    val createdAt: CreatedAt = CreatedAt(LocalDateTime.now()),
    var failReason: FailReason? = null
) {
    fun create(operationRepository: OperationRepository): Operation {
        operationRepository.save(this)
        return this
    }

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