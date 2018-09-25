package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.CustomerApi
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.command.handler.CustomerCommandHandler
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

//    override fun find(@PathVariable("id") id: String): CustomerResponse {
//        return findCustomer(id)
//    }

    /*override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse {
        return updateCustomer(id, request)
    }

    override fun delete(@PathVariable("id") id: String) {
        customers.remove(id)
    }

    private fun saveCustomer(request: CreateCustomerRequest): CustomerResponse {
        val id = UUID.randomUUID().toString()
        customers[id] = CustomerResponse(
            id = id,
            name = request.name,
            status = "ATIVO!",
            document = CustomerResponse.DocumentResponse(
                type = request.document.type,
                number = request.document.number
            ),
            createdAt = LocalDateTime.now()
        )
        return findCustomer(id)
    }

    private fun findCustomer(id: String): CustomerResponse =
        Optional.ofNullable(customers[id])
            .orElseThrow {
                Exception()
            }

    private fun updateCustomer(id: String, request: UpdateCustomerRequest): CustomerResponse {
        customers[id] = CustomerResponse(
            id = id,
            name = request.name,
            status = "ATIVO!",
            document = CustomerResponse.DocumentResponse(
                type = request.document.type,
                number = request.document.number
            ),
            createdAt = LocalDateTime.now()
        )

        return findCustomer(id)
    }*/
}
