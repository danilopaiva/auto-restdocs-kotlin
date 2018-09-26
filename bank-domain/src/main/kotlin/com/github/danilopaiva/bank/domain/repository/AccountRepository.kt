package com.github.danilopaiva.bank.domain.repository

import com.github.danilopaiva.bank.domain.Account

interface AccountRepository {

    fun save(account: Account): Int

    fun find(id: Account.Id): Account?

    fun update(id: Account.Id, status: Account.Status): Int
}