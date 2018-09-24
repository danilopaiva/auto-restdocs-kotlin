package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.CustomerApi
import com.github.danilopaiva.bank.api.request.CustomerRequest
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

    override fun create(@RequestBody @Valid request: CustomerRequest): CustomerResponse {
        return saveCustomer(request)
    }

    override fun find(@PathVariable("id") id: String): CustomerResponse {
        return Optional.ofNullable(customers[id])
            .orElseThrow {
                Exception()
            }
    }

    override fun update(@PathVariable("id") id: String): CustomerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(@PathVariable("id") id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveCustomer(request: CustomerRequest): CustomerResponse {
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
        return Optional.ofNullable(customers[id])
            .orElseThrow {
                Exception()
            }
    }
}
