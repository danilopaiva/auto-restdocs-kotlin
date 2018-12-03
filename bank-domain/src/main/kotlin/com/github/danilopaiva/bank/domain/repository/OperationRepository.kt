package com.github.danilopaiva.bank.domain.repository

import com.github.danilopaiva.bank.domain.Operation

interface OperationRepository {

    fun save(operation: Operation) : Int
}