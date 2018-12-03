package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.github.danilopaiva.bank.domain.repository.OperationRepository
import java.util.*

class Account(
    val id: Id = Id(),
    val customerId: Customer.Id,
    val type: Type,
    var status: Status = Status.ACTIVATED,
    var amount: Amount = Amount.initialBalance(),
    val createdAt: CreatedAt = CreatedAt.now(),
    val operations: List<Operation> = listOf()
) {
    fun create(repository: AccountRepository): Account {
        //TODO create validations
        repository.save(this)
        return this
    }

    fun update(repository: AccountRepository, status: Status): Account {
        repository.update(this.id, status)
        this.status = status
        return this
    }

    fun deposit(
        repository: AccountRepository,
        operationRepository: OperationRepository,
        amount: Account.Amount
    ): Operation {
        verifyIfAccountIsActive()
        repository.update(id, amount)
        this.amount = this.amount + amount
        return createOperation(operationRepository, amount)
    }

    fun withdraw(repository: AccountRepository, amountWithdraw: Account.Amount) {
        verifyIfAccountIsActive()
        verifyAmountIsSufficient(amountWithdraw)
        this.amount = this.amount.withdraw(amountWithdraw)
        repository.update(this.id, this.amount)
    }

    private fun verifyAmountIsSufficient(amount: Amount) {
        if (this.amount.value < amount.value) {
            throw Exception("I'm sorry, but you have insufficient balance. Amount: $${this.amount.value}") //TODO update exception
        }
    }

    private fun verifyIfAccountIsActive() {
        if (Status.ACTIVATED != this.status) {
            throw Exception("Operation isn't allowed to account status ${this.status.name}") //TODO update exception
        }
    }

    private fun createOperation(operationRepository: OperationRepository, amount: Account.Amount): Operation {
        return Operation(
            accountId = id,
            value = Operation.Value(amount.value),
            type = Operation.Type.DEPOSIT,
            status = Operation.Status.SUCCESS
        ).create(operationRepository)
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

    data class Amount(val value: Double) {
        fun withdraw(amount: Account.Amount): Account.Amount {
            return Amount(this.value - amount.value)
        }

        companion object {
            private const val INITIAL_BALANCE = 0.0

            fun initialBalance() =
                Amount(INITIAL_BALANCE)

        }

        operator fun plus(amount: Amount): Amount =
            Amount(value + amount.value)
    }
}