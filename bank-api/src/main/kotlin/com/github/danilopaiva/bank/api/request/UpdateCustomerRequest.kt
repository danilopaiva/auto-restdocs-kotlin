package com.github.danilopaiva.bank.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Update Customer Request
 */
class UpdateCustomerRequest(
    /**
     * Customer Name
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val name: String?,

    /**
     * Customer Email
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val email: String?,

    /**
     * Customer Document
     */
    @field:NotNull
    val document: DocumentRequest?
)
