package com.github.danilopaiva.bank.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Create Customer Request
 */
class CustomerRequest(
    /**
     * Customer Name
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val name: String?,

    /**
     * Customer Document
     */
    @field:NotNull
    val document: DocumentRequest
) {

    /**
     * Document Request
     */
    class DocumentRequest(
        /**
         * Document Type
         */
        @field:[NotNull Size(min = 1, max = 500)]
        val type: String?,

        /**
         * Document Number
         */
        @field:[NotNull]
        val number: String?
    )
}
