package br.com.leechers.representation

import java.time.LocalDateTime

data class LeecherRepresentation(
        val id: String,
        val name: String?,
        val description: String?,
        val stolenMoney: Double?,
        val createAt: LocalDateTime,
        val status: String
)