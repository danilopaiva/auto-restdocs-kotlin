package com.github.danilopaiva.bank.repository

import com.github.danilopaiva.bank.domain.Operation
import com.github.danilopaiva.bank.domain.repository.OperationRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
open class JdbcOperationRepository(private val jdbcTemplate: JdbcTemplate) : OperationRepository {

    companion object {
        const val TABLE_NAME = "operation"
        const val ID_COLUMN = "id"
        const val ACCOUNT_ID_COLUMN = "account_id"
        const val TYPE_COLUMN = "type"
        const val VALUE_COLUMN = "value"
        const val TO_ACCOUNT_ID_COLUMN = "to_account_id"
        const val STATUS_COLUMN = "status"
        const val FAIL_REASON_COLUMN = "fail_reason"
        const val CREATED_AT_COLUMN = "created_at"
        const val COMPLETED_AT_COLUMN = "completed_at"
    }

    override fun save(operation: Operation): Int {
        val sql = """
           insert into $TABLE_NAME ($ID_COLUMN, $ACCOUNT_ID_COLUMN, $TYPE_COLUMN, $VALUE_COLUMN, $TO_ACCOUNT_ID_COLUMN,
                                    $STATUS_COLUMN, $CREATED_AT_COLUMN)
                        values (?, ?, ?, ?, ?, ?, now())
        """
        return jdbcTemplate.update(
            sql,
            operation.id.value,
            operation.accountId.value,
            operation.type.name,
            operation.value.value,
            operation.toAccountId?.value,
            operation.status.name
        )
    }
}