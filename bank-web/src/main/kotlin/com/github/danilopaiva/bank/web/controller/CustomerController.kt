package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.CustomerApi
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid

/**
 * Customer controller
 */
@RestController
class CustomerController : CustomerApi {

    companion object {
        private val customers = hashMapOf<String, CustomerResponse>()
    }

    override fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse {
        return saveCustomer(request)
    }

    override fun find(@PathVariable("id") id: String): CustomerResponse {
        return findCustomer(id)
    }

    override fun update(
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
            createAt = LocalDateTime.now()
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
            createAt = LocalDateTime.now()
        )

        return findCustomer(id)
    }
}
