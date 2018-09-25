package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.objectToJson
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import com.github.danilopaiva.bank.repository.extractor.CustomerRowMapper
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
open class JdbcCustomerRepository(private val jdbcTemplate: JdbcTemplate) : CustomerRepository {

    companion object {
        const val TABLE_NAME = "customer"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"
        const val EMAIL_COLUMN = "email"
        const val DOCUMENT_COLUMN = "document"
        const val CREATED_AT_COLUMN = "created_at"
    }

    override fun save(customer: Customer): Int {
        val sql = """
            insert into $TABLE_NAME ($ID_COLUMN, $NAME_COLUMN, $EMAIL_COLUMN, $DOCUMENT_COLUMN, $CREATED_AT_COLUMN)
                values (?,?,?,?::JSON, now())
            """
        return jdbcTemplate.update(
            sql,
            customer.id.value,
            customer.name.value,
            customer.email.value,
            customer.document.objectToJson()
        )
    }

    override fun find(customerId: Customer.Id): Customer? {
        val sql = """
            select * from $TABLE_NAME where $ID_COLUMN = ?
            """
        return try {
            jdbcTemplate.queryForObject(sql, CustomerRowMapper(), customerId.value)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override fun update(customer: Customer): Int {
        val sql = """
            update $TABLE_NAME set $NAME_COLUMN = ?, $EMAIL_COLUMN = ?, $DOCUMENT_COLUMN = ?::JSON
                where $ID_COLUMN = ?
            """
        return jdbcTemplate.update(
            sql,
            customer.name.value,
            customer.email.value,
            customer.document.objectToJson(),
            customer.id.value
        )
    }

    override fun delete(id: Customer.Id): Int {
        val sql = """
            delete from $TABLE_NAME where $ID_COLUMN = ?
            """
        return jdbcTemplate.update(sql, id.value)
    }
}