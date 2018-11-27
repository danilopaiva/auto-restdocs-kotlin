package com.github.danilopaiva.bank.api

import com.github.danilopaiva.bank.api.request.CreateAccountRequest
import com.github.danilopaiva.bank.api.request.UpdateAccountRequest
import com.github.danilopaiva.bank.api.request.ValueRequest
import com.github.danilopaiva.bank.api.response.AccountResponse
import com.github.danilopaiva.bank.api.response.OperationResponse
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

    /**
     * To do deposit
     * @param accountId Account Id
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{accountId}/deposit")
    fun deposit(
        @PathVariable("accountId") accountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse

    /**
     * To do withdraw
     * @param accountId Account Id
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{accountId}/withdraw")
    fun withdraw(
        @PathVariable("accountId") accountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse

    /**
     * To do transfer
     * @param fromAccountId From account id
     * @param toAccountId To account id
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/from/{fromAccountId}/to/{toAccountId}/transfer")
    fun transfer(
        @PathVariable("fromAccountId") fromAccountId: String,
        @PathVariable("toAccountId") toAccountId: String,
        @RequestBody @Valid request: ValueRequest
    ): OperationResponse

    /**
     * Report
     * @param accountId Account Id
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping("/{accountId}/reports")
    fun report(@PathVariable("accountId") accountId: String): OperationResponse

    /**
     * Report by id
     * @param accountId Account Id
     * @param reportId Account Id
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping("/{accountId}/reports/{reportId}")
    fun reportById(
        @PathVariable("accountId") accountId: String,
        @PathVariable("reportId") reportId: String
    ): OperationResponse
}