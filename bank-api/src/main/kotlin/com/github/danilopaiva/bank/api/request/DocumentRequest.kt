package com.github.danilopaiva.bank.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Document Request
 */
class DocumentRequest(
    /**
     * Document Type
     */
    @field:NotNull
    val type: String?,

    /**
     * Document Number
     */
    @field:[NotNull Size(min = 1, max = 20)]
    val number: String?
)