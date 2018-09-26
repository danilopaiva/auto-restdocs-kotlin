package com.github.danilopaiva.bank.api.response

import java.time.LocalDateTime

/**
 * Account Response
 */
class AccountResponse(
    /**
     * Account Id
     */
    val id: String,

    /**
     * Customer Id
     */
    val customerId: String,

    /**
     * Account Type
     */
    val type: String,

    /**
     * Account Status
     */
    val status: String,

    /**
     * Account Amount
     */
    val amount: Double,

    /**
     * Customer created at
     */
    val createdAt: LocalDateTime
)