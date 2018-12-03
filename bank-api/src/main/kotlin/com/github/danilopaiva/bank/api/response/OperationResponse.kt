package com.github.danilopaiva.bank.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

/**
 *  Operation Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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
