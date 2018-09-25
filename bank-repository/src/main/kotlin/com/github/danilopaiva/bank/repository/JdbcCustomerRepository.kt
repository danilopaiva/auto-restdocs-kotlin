package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.objectToJson
import com.github.danilopaiva.bank.domain.repository.CustomerRepository
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
    }

    override fun save(customer: Customer): Int {
        val sql = """
            insert into $TABLE_NAME ($ID_COLUMN, $NAME_COLUMN, $EMAIL_COLUMN, $DOCUMENT_COLUMN)
                values (?,?,?,?::JSON)
            """
        return jdbcTemplate.update(
            sql,
            customer.id.value,
            customer.name.value,
            customer.email.value,
            customer.document.objectToJson()
        )
    }
}