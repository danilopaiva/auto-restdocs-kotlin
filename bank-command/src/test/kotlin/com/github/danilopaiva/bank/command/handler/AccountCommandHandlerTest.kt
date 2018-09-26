package com.github.danilopaiva.bank.command.handler

import com.github.danilopaiva.bank.command.UpdateAccount
import com.github.danilopaiva.bank.command.CreateAccount
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.dummyAccount
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class AccountCommandHandlerTest {

    private val repository: AccountRepository = mock()
    private lateinit var commandHandler: AccountCommandHandler

    @Before
    fun setup() {
        commandHandler = AccountCommandHandler(repository)
    }

    @Test
    fun `verify if the repository was called to save an account`() {
        val command = dummyCommandCreateAccount()
        commandHandler.handler(command)
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `verify if the repository was called to close an account`() {
        val status = Account.Status.CLOSED
        val account = dummyAccount()
        whenever(repository.find(account.id)).thenReturn(account)

        val command = dummyCommandCloseAccount(id = account.id, status = status)
        commandHandler.handler(command)
        verify(repository, times(1)).update(account.id, status)
    }

    private fun dummyCommandCreateAccount() =
        CreateAccount(
            customerId = Customer.Id(),
            type = Account.Type.CURRENT
        )

    private fun dummyCommandCloseAccount(
        id: Account.Id = Account.Id(),
        status: Account.Status = Account.Status.CLOSED
    ) =
        UpdateAccount(
            id = id,
            status = status
        )
}