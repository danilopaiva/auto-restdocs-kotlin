package br.com.leeches.representation

import java.time.LocalDateTime

/**
 * Leech representation
 */
data class LeechRepresentation(
        /**
         * Leech identifier
         */
        val id: String,

        /**
         * Name of leech
         */
        val name: String?,

        /**
         * Description of leech
         */
        val description: String?,

        /**
         * Amount of money stolen by the leech
         */
        val stolenMoney: Double?,

        /**
         * Date of registration
         */
        val createAt: LocalDateTime,

        /**
         * Leech situation
         */
        val status: String
)
