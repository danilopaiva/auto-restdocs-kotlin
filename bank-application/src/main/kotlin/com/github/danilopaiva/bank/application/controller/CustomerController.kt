package com.github.danilopaiva.bank.application.controller

import com.github.danilopaiva.bank.api.CustomerApi
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.application.helper.toDomain
import com.github.danilopaiva.bank.application.helper.toResponse
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Customer controller
 */
@RestController
class CustomerController(private val repository: CustomerRepository) : CustomerApi {

    override fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse {
        return request.toDomain().create(repository).toResponse()
    }

    override fun find(@PathVariable("id") id: String): CustomerResponse {
        return find(Customer.Id(id)).toResponse()
    }

    override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse {
        val customerId = find(Customer.Id(id)).id
        return request
            .toDomain(customerId)
            .update(repository)
            .toResponse()
    }

    override fun delete(@PathVariable("id") id: String) {
        repository.delete(Customer.Id(id))
    }

    private fun find(customerId: Customer.Id): Customer {
        return repository.find(customerId) ?: throw Exception() //TODO update exception
    }
}
