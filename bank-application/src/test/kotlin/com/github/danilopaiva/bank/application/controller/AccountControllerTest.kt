package com.github.danilopaiva.bank.application.controller

import com.github.danilopaiva.bank.api.request.ValueRequest
import com.github.danilopaiva.bank.api.response.AccountResponse
import com.github.danilopaiva.bank.api.response.OperationResponse
import com.github.danilopaiva.bank.domain.Account
import com.github.danilopaiva.bank.domain.helper.jsonToObject
import com.github.danilopaiva.bank.domain.helper.objectToJson
import com.github.danilopaiva.bank.application.config.ControllerBaseTest
import com.github.danilopaiva.bank.domain.Operation
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class AccountControllerTest : ControllerBaseTest() {

    @Test
    fun `should create a account`() {
        val id = createCustomer()
        val account = dummyCreateAccountRequest(id)

        this.mockMvc.perform(
            post("/accounts")
                .content(account.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(AccountResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(id, customerId)
                    assertEquals(Account.Status.ACTIVATED.name, status)
                    assertEquals(0.0, amount)
                }
            }
    }

//    TODO: create exception handler to return not found code
/*    @Test
    fun `should fail create a account because customer doesn't exist`() {
        val account = dummyCreateAccountRequest()

        this.mockMvc.perform(
            post("/accounts")
                .content(account.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }*/

    @Test
    fun `should find a account already created`() {
        val accountId = createAccount()
        this.mockMvc.perform(get("/accounts/{id}", accountId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(AccountResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(accountId, id)
                }
            }
    }

    @Test
    fun `should close a account already created and without amount`() {
        val accountId = createAccount()
        val updateRequest = dummyUpdateAccountRequest(Account.Status.CLOSED)
        this.mockMvc.perform(
            put("/accounts/{id}", accountId)
                .content(updateRequest.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(AccountResponse::class.java).run {
                    assertEquals(accountId, id)
                    assertEquals(Account.Status.CLOSED.name, status)
                }
            }
    }

    @Test
    fun `should to do a deposit`() {
        val accountIdCreated = createAccount()
        val request = ValueRequest(100.0)
        this.mockMvc.perform(
            post("/accounts/{id}/deposit", accountIdCreated)
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect {
                it.response.contentAsString.jsonToObject(OperationResponse::class.java).run {
                    assertNotNull(id)
                    assertNotNull(createdAt)
                    assertEquals(accountIdCreated, accountId)
                    assertEquals(request.value, value)
                    assertEquals(Operation.Type.DEPOSIT.name, type)
                    assertEquals(Operation.Status.SUCCESS.name, status)
                }
            }
    }

    @Ignore
    @Test
    fun `should to do a withdraw`() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    @Test
    fun `should to do a transference`() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    @Test
    fun `should to find a operation`() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
