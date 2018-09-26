package com.github.danilopaiva.bank.command.handler

import com.github.danilopaiva.bank.command.UpdateAccount
import com.github.danilopaiva.bank.command.CreateAccount
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
open class AccountCommandHandler(private val repository: AccountRepository) {

    fun handler(command: CreateAccount): Account {
        val account = Account(
            customerId = command.customerId,
            type = command.type
        )

        return account.create(repository)
    }

    fun handler(command: UpdateAccount): Account {
        val account = find(command.id)
        return account.update(repository, command.status)
    }

    fun find(id: Account.Id): Account {
        return Optional.ofNullable(repository.find(id))
            .orElseThrow {
                //TODO update exception
                Exception()
            }
    }
}