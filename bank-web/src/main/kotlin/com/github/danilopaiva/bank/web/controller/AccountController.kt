package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.AccountApi
import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.UpdateAccountRequest
import com.github.danilopaiva.bank.api.response.AccountResponse
import com.github.danilopaiva.bank.command.handler.AccountCommandHandler
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.web.helper.toCommand
import com.github.danilopaiva.bank.web.helper.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Account controller
 */
@RestController
class AccountController(private val commandHandler: AccountCommandHandler) : AccountApi {

    override fun create(@RequestBody @Valid request: CreateAccountRequest): AccountResponse {
        return request.run { this.toCommand() }
            .run { commandHandler.handler(this) }
            .run { this.toResponse() }
    }

    override fun find(@PathVariable("id") id: String): AccountResponse {
        return commandHandler.find(Account.Id(id)).toResponse()
    }

    override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateAccountRequest
    ): AccountResponse {
        return commandHandler.handler(request.toCommand(Account.Id(id))).toResponse()
    }
}
