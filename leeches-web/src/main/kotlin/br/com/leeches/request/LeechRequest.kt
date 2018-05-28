package br.com.leeches.request

data class LeechRequest(
        //@field:[NotNull Min(1) Max(100)]
        val name: String?,
        //@field:[Max(500)]
        val description: String?,
        //@field:[NotNull]
        val stolenMoney: Double?
)