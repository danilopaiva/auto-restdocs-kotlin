package com.github.danilopaiva.bank.domain

import java.time.LocalDateTime

class CreatedAt(val value: LocalDateTime) {

    companion object {
        fun now() =
            CreatedAt(LocalDateTime.now())
    }
}