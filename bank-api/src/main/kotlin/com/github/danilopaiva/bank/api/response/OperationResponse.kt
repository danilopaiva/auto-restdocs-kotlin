package com.github.danilopaiva.bank.api.response

import java.time.LocalDateTime

/**
 *  Operation Response
 */
class OperationResponse(
    /**
     * Operation id
     */
    val id: String,

    /**
     * Account id
     */
    val accountId: String,

    /**
     * Destination account
     */
    val toAccountId: String? = null,

    /**
     * Value monetary this operation
     */
    val value: Double,

    /**
     * Type operation
     */
    val type: String,

    /**
     * Status operation
     */
    val status: String,

    /**
     * Created at
     */
    val createdAt: LocalDateTime,

    /**
     * Description when there any fail
     */
    val failReason: String? = null
)
