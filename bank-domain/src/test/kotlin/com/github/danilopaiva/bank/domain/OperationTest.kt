package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.helper.dummyOperation
import com.github.danilopaiva.bank.domain.repository.OperationRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class OperationTest {

    private val repository: OperationRepository = mock()

    @Test
    fun `should create an operation`() {
        val operation = dummyOperation()
        operation.create(repository)
        verify(repository, times(1)).save(operation)
    }
}