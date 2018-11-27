package com.github.danilopaiva.bank.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Update Account Request
 */
class UpdateAccountRequest(
    /**
     * Status to update the account
     */
    @field:[NotNull Size(min = 1, max = 50)] //TODO UPDATE TO PARTNER
    val status: String?
)