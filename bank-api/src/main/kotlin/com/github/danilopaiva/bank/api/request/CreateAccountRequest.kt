package com.github.danilopaiva.bank.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Create Account Request
 */
class CreateAccountRequest(

    /**
     * Customer id to create account
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val customerId: String?,

    /**
     * Account Type
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val type: String?
)