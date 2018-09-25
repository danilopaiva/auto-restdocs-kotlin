package com.github.danilopaiva.bank.repository.extractor

import com.github.danilopaiva.bank.domain.CreatedAt
import com.github.danilopaiva.bank.domain.Customer
import com.github.danilopaiva.bank.domain.helper.jsonToObject
import com.github.danilopaiva.bank.repository.JdbcCustomerRepository
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CustomerRowMapper : RowMapper<Customer> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Customer {
        val id = rs.getString(JdbcCustomerRepository.ID_COLUMN)
        val name = rs.getString(JdbcCustomerRepository.NAME_COLUMN)
        val email = rs.getString(JdbcCustomerRepository.EMAIL_COLUMN)
        val document = rs.getString(JdbcCustomerRepository.DOCUMENT_COLUMN)
        val createdAt = rs.getTimestamp(JdbcCustomerRepository.CREATED_AT_COLUMN)

        return Customer(
            id = Customer.Id(id),
            name = Customer.Name(name),
            email = Customer.Email(email),
            document = document.jsonToObject(Customer.Document::class.java),
            createdAt = CreatedAt(createdAt.toLocalDateTime())
        )
    }
}