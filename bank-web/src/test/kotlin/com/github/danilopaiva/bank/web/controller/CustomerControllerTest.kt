package com.github.danilopaiva.bank.web.controller

import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.web.config.ControllerBaseTest
import com.github.danilopaiva.bank.web.extension.jsonToObject
import com.github.danilopaiva.bank.web.extension.objectToJson
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
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
                    assertNotNull(createAt)
                    assertNotNull(status)
                    assertEquals(customer.name, name)
                    assertEquals(customer.document.number, document.number)
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
                    assertNotNull(createAt)
                    assertNotNull(status)
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
                    assertNotNull(createAt)
                    assertNotNull(status)
                    assertEquals(customerId, id)
                    assertNotEquals(customerToCreate.name, name)
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
