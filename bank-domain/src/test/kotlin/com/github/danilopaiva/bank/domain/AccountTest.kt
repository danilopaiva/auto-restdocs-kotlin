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

    @Test
    fun `should do a deposit in active account`() {
        val amount = Account.Amount(10.0)
        val account = dummyAccount()
        account.deposit(repository, amount)
        assertEquals(amount, account.amount)
    }

    @Test(expected = Exception::class)
    fun `shouldn't do a deposit because the account is inactivated`() {
        val amount = Account.Amount(10.0)
        val account = dummyAccount()
        account.update(repository, Account.Status.INACTIVATED)
        account.deposit(repository, amount)
    }

    @Test(expected = Exception::class)
    fun `should throw a exception because balance is insufficient to withdraw`() {
        val account = dummyAccount()
        val amountWithdraw = Account.Amount(7.0)

        account.withdraw(repository, amountWithdraw)
    }

    @Test
    fun `should do a withdraw in active account`() {
        val amountDeposit = Account.Amount(10.0)
        val account = dummyAccount()
        account.deposit(repository, amountDeposit)

        val amountWithdraw = Account.Amount(7.0)
        account.withdraw(repository, amountWithdraw)
        assertEquals(amountDeposit.value - amountWithdraw.value, account.amount.value)
    }

    @Test
    fun `should close an account when amount is zero`() {

    }

}