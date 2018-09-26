package com.github.danilopaiva.bank.api.response

import java.time.LocalDateTime

/**
 * Customer Response
 */
data class CustomerResponse(
    /**
     * Customer Id
     */
    val id: String,

    /**
     * Customer Name
     */
    val name: String,

    /**
     * Customer Email
     */
    val email: String,

    /**
     * Customer Document
     */
    val document: DocumentResponse,

    /**
     * Customer created at
     */
    val createdAt: LocalDateTime
) {
    /**
     * Document Response
     */
    class DocumentResponse(
        /**
         * Document Type
         */
        val type: String?,

        /**
         * Document Number
         */
        val number: String?
    )
}
