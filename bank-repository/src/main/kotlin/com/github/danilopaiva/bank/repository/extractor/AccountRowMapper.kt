package com.github.danilopaiva.bank.repository.extractor

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.CreatedAt
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.repository.JdbcAccountRepository
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class AccountRowMapper : RowMapper<Account> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Account {
        val id = rs.getString(JdbcAccountRepository.ID_COLUMN)
        val customerId = rs.getString(JdbcAccountRepository.CUSTOMER_ID_COLUMN)
        val type = rs.getString(JdbcAccountRepository.TYPE_COLUMN)
        val status = rs.getString(JdbcAccountRepository.STATUS_COLUMN)
        val amount = rs.getDouble(JdbcAccountRepository.AMOUNT_COLUMN)
        val createdAt = rs.getTimestamp(JdbcAccountRepository.CREATED_AT_COLUMN)

        return Account(
            id = Account.Id(id),
            customerId = Customer.Id(customerId),
            type = enumValueOf(type),
            status = enumValueOf(status),
            amount = Account.Amount(amount),
            createdAt = CreatedAt(createdAt.toLocalDateTime())
        )
    }
}