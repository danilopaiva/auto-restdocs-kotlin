package com.github.danilopaiva.bank.domain

import com.github.danilopaiva.bank.domain.repository.CustomerRepository
import java.util.*

data class Customer(
    val id: Id = Id(),
    val name: Name,
    val email: Email,
    val document: Document,
    val createdAt: CreatedAt = CreatedAt.now()
) {

    fun create(repository: CustomerRepository): Customer {
        //you can create validations
        repository.save(this)
        return this
    }

    fun update(repository: CustomerRepository): Customer {
        //you can create validations
        repository.update(this)
        return this
    }

    fun delete(repository: CustomerRepository) {
        //you can create validations
        repository.delete(this.id)
    }


    data class Id(val value: String = UUID.randomUUID().toString())

    data class Name(val value: String)

    data class Email(val value: String)

    data class Document(val type: Type, val number: Number) {
        enum class Type {
            IDENTITY,
            DRIVER_LICENSE,
            PASSPORT
        }

        data class Number(val value: String)
    }
}