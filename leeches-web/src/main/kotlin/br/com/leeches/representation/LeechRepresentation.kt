package br.com.leeches.representation

import java.time.LocalDateTime

data class LeechRepresentation(
        val id: String,
        val name: String?,
        val description: String?,
        val stolenMoney: Double?,
        val createAt: LocalDateTime,
        val status: String
)