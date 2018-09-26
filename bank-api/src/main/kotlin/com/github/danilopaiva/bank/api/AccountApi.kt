package com.github.danilopaiva.bank.api

import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.UpdateAccountRequest
import com.github.danilopaiva.bank.api.response.AccountResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
 * Account API
 */
@RequestMapping("/accounts")
interface AccountApi {

    /**
     * Create account
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun create(@RequestBody @Valid request: CreateAccountRequest): AccountResponse

    /**
     * @param id Account Id
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}", produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun find(@PathVariable("id") id: String): AccountResponse

    /**
     * Close the account
     * @param id Account Id
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping("/{id}", produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateAccountRequest
    ): AccountResponse
}