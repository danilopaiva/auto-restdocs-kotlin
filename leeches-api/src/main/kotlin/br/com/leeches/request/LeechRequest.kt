package br.com.leeches.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Leech request
 */
class LeechRequest(
        /**
         * Name of leech.
         */
        @field:[NotNull Size(min = 1, max = 50)]
        val name: String?,

        /**
         * Description of leech.
         */
        @field:[NotNull Size(min = 1, max = 500)]
        val description: String?,

        /**
         * Amount of money stolen by the leech.
         */
        @field:[NotNull]
        val stolenMoney: Double?
)
