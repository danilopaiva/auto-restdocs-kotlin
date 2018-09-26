package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.repository.AccountRepository
import java.util.*

class Account(
    val id: Id = Id(),
    val customerId: Customer.Id,
    val type: Type,
    var status: Status = Status.ACTIVATED,
    val amount: Amount = Amount.initialBalance(),
    val createdAt: CreatedAt = CreatedAt.now(),
    val transactions: List<Transaction> = listOf()
) {
    fun create(repository: AccountRepository): Account {
        //TODO create validations
        repository.save(this)
        return this
    }

    fun update(repository: AccountRepository, status: Status): Account {
        this.status = status
        repository.update(this.id, status)
        return this
    }

    class Id(val value: String = UUID.randomUUID().toString())

    enum class Type {
        CURRENT
    }

    enum class Status {
        ACTIVATED,
        INACTIVATED,
        CLOSED
    }

    class Amount(val value: Double) {
        companion object {
            private const val INITIAL_BALANCE = 0.0

            fun initialBalance() =
                Amount(INITIAL_BALANCE)

        }
    }
}