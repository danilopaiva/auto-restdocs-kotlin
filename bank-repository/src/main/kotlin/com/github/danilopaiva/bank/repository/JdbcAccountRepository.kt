package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.repository.AccountRepository
import com.github.danilopaiva.bank.repository.extractor.AccountRowMapper
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
open class JdbcAccountRepository(private val jdbcTemplate: JdbcTemplate) : AccountRepository {

    companion object {
        const val TABLE_NAME = "account"
        const val ID_COLUMN = "id"
        const val CUSTOMER_ID_COLUMN = "customer_id"
        const val TYPE_COLUMN = "type"
        const val STATUS_COLUMN = "status"
        const val AMOUNT_COLUMN = "amount"
        const val CREATED_AT_COLUMN = "created_at"
    }

    override fun save(account: Account): Int {
        val sql = """
            insert into $TABLE_NAME ($ID_COLUMN, $CUSTOMER_ID_COLUMN,
                        $TYPE_COLUMN, $STATUS_COLUMN, $AMOUNT_COLUMN, $CREATED_AT_COLUMN)
                values (?,?,?,?,?, now())
            """
        return jdbcTemplate.update(
            sql,
            account.id.value,
            account.customerId.value,
            account.type.name,
            account.status.name,
            account.amount.value
        )
    }

    override fun find(id: Account.Id): Account? {
        val sql = """
            select * from $TABLE_NAME where $ID_COLUMN = ?
            """
        return try {
            jdbcTemplate.queryForObject(sql, AccountRowMapper(), id.value)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override fun update(id: Account.Id, status: Account.Status): Int {
        val sql = """
            update $TABLE_NAME set $STATUS_COLUMN = ? where $ID_COLUMN = ?
            """
        return jdbcTemplate.update(
            sql,
            status.name,
            id.value
        )
    }
}