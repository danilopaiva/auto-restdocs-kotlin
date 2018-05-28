package br.com.leechers.controller

import br.com.leechers.ControllerBaseTest
import br.com.leechers.extension.jsonToObject
import br.com.leechers.extension.objectToJson
import br.com.leechers.representation.LeecherRepresentation
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LeechersControllerTest : ControllerBaseTest() {

    @Test
    fun `save new leecher`() {
        val leecher = getLeechersRequest()

        this.mockMvc.perform(post("/leechers")
                .content(leecher.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect {
                    val response =
                            it.response.contentAsString.jsonToObject(LeecherRepresentation::class.java)
                    assertNotNull(response.id)
                    assertNotNull(response.createAt)
                    assertNotNull(response.status)
                    assertEquals(leecher.name, response.name)
                    assertEquals(leecher.description, response.description)
                    assertEquals(leecher.stolenMoney, response.stolenMoney)
                }
    }

    @Test
    fun `find a leecher`() {
        val leecherId = createLeecher()
        this.mockMvc.perform(get("/leechers/{id}", leecherId))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect {
                    val response =
                            it.response.contentAsString.jsonToObject(LeecherRepresentation::class.java)
                    assertNotNull(response.id)
                    assertNotNull(response.createAt)
                    assertNotNull(response.status)
                    assertEquals(leecherId, response.id)
                }
    }
}