package com.github.danilopaiva.bank.api

import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Customer API
 */
@RequestMapping("/customers")
interface CustomerApi {

    /**
     * Create customer
     */
    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse

    /**
     * @param id Customer Id
     */
    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun find(@PathVariable("id") id: String): CustomerResponse

    /**
     * @param id Customer Id
     */
    @ResponseStatus(OK)
    @ResponseBody
    @PutMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse

    /**
     * @param id Customer Id
     */
    @ResponseStatus(NO_CONTENT)
    @ResponseBody
    @DeleteMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun delete(@PathVariable("id") id: String)
}