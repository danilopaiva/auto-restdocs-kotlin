package com.github.danilopaiva.bank.application.controller

import com.github.danilopaiva.bank.api.AccountApi
import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.UpdateAccountRequest
import com.github.danilopaiva.bank.api.request.ValueRequest
import com.github.danilopaiva.bank.api.response.AccountResponse
import com.github.danilopaiva.bank.api.response.OperationResponse
import com.github.danilopaiva.bank.application.helper.toDomain
import com.github.danilopaiva.bank.application.helper.toResponse
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.github.danilopaiva.bank.domain.repository.OperationRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Account controller
 */
@RestController
class AccountController(
    private val repository: AccountRepository,
    private val operationRepository: OperationRepository
) : AccountApi {

    override fun create(@RequestBody @Valid request: CreateAccountRequest): AccountResponse {
        return request.toDomain().create(repository).toResponse()
    }

    override fun find(@PathVariable("id") id: String): AccountResponse {
        return find(Account.Id(id)).toResponse()
    }

    override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateAccountRequest
    ): AccountResponse {
        return find(Account.Id(id)).update(repository, enumValueOf(request.status!!))
            .toResponse()
    }

    override fun deposit(
        @PathVariable("accountId") accountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse {
        val account = find(Account.Id(accountId))
        return account.deposit(repository, operationRepository, Account.Amount(request.value!!))
            .toResponse()
    }

    override fun withdraw(
        @PathVariable("accountId") accountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transfer(
        @PathVariable("fromAccountId") fromAccountId: String,
        @PathVariable("toAccountId") toAccountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun report(@PathVariable("accountId") accountId: String): OperationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportById(
        @PathVariable("accountId") accountId: String,
        @PathVariable("reportId") reportId: String
    ): OperationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun find(id: Account.Id): Account {
        return repository.find(id) ?: throw Exception()   //TODO update exception
    }
}
