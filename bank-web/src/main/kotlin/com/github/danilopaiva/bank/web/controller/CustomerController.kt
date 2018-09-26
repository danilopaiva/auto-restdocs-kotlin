package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.CustomerApi
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.command.DeleteCustomer
import com.github.danilopaiva.bank.command.handler.CustomerCommandHandler
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.web.helper.toCommand
import com.github.danilopaiva.bank.web.helper.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Customer controller
 */
@RestController
class CustomerController(private val commandHandler: CustomerCommandHandler) : CustomerApi {

    override fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse {
        return request.run { this.toCommand() }
            .run { commandHandler.handler(this) }
            .run { this.toResponse() }
    }

    override fun find(@PathVariable("id") id: String): CustomerResponse {
        return commandHandler.find(Customer.Id(id)).toResponse()
    }

    override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse {
        return request.run { this.toCommand(Customer.Id(id)) }
            .run { commandHandler.handler(this) }
            .run { this.toResponse() }
    }

    override fun delete(@PathVariable("id") id: String) {
        val command = DeleteCustomer(Customer.Id(id))
        command.apply { commandHandler.handler(this) }
    }
}
