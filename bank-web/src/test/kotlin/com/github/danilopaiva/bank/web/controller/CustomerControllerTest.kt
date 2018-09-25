package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.domain.helper.jsonToObject
import com.github.danilopaiva.bank.domain.helper.objectToJson
import com.github.danilopaiva.bank.web.config.ControllerBaseTest
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class CustomerControllerTest : ControllerBaseTest() {

    @Test
    fun `should create a customer`() {
        val customer = dummyCreateCustomerRequest()

        this.mockMvc.perform(
            post("/customers")
                .content(customer.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(CustomerResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(customer.name, name)
                    assertEquals(customer.document?.number, document.number)
                }
            }
    }

    @Test
    fun `should find a customer already created`() {
        val customerId = createCustomer()
        this.mockMvc.perform(get("/customers/{id}", customerId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(CustomerResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(customerId, id)
                }
            }
    }

    @Test
    fun `should update a customer already created`() {
        val customerToCreate = dummyCreateCustomerRequest()
        val customerToUpdate = dummyUpdateCustomerRequest()
        val customerId = createCustomer(customerToCreate)

        this.mockMvc.perform(
            put("/customers/{id}", customerId)
                .content(customerToUpdate.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(CustomerResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(customerId, id)
                    assertEquals(customerToUpdate.name, name)
                    assertEquals(customerToUpdate.email, email)
                    assertEquals(customerToUpdate.document?.number, document.number)
                }
            }
    }

    @Test
    fun `should delete a customer already created`() {
        val customerId = createCustomer()
        this.mockMvc.perform(delete("/customers/{id}", customerId))
            .andExpect(status().isNoContent)
    }
}
