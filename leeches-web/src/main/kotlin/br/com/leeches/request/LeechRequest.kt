package br.com.leeches.request

import javax.validation.constraints.NotNull

class LeechRequest(
        @field:[NotNull]
        val name: String?,
        @field:[NotNull]
        val description: String?,
        @field:[NotNull]
        val stolenMoney: Double?
)