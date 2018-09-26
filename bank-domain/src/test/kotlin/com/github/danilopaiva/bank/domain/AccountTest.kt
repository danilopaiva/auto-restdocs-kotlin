package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.helper.dummyAccount
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import kotlin.test.assertEquals

class AccountTest {

    private val repository: AccountRepository = mock()

    @Test
    fun `verify if the repository was called to save an account`() {
        val account = dummyAccount()
        account.create(repository)
        verify(repository, times(1)).save(account)
    }

    @Test
    fun `verify if the repository was called to close an account`() {
        val account = dummyAccount()
        val status = Account.Status.CLOSED
        account.update(repository, status)
        assertEquals(status, account.status)
        verify(repository, times(1)).update(account.id, status)
    }
}