package br.com.leeches.controller

import br.com.leeches.ControllerBaseTest
import br.com.leeches.extension.jsonToObject
import br.com.leeches.extension.objectToJson
import br.com.leeches.representation.LeechRepresentation
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class LeechesControllerTest : ControllerBaseTest() {

    @Test
    fun `create leech success`() {
        val leech = getLeechRequest()

        this.mockMvc.perform(post("/leeches")
                .content(leech.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect {
                    it.response.contentAsString.jsonToObject(LeechRepresentation::class.java).run {
                        assertNotNull(id)
                        assertNotNull(createAt)
                        assertNotNull(status)
                        assertEquals(leech.name, name)
                        assertEquals(leech.description, description)
                        assertEquals(leech.stolenMoney, stolenMoney)
                    }
                }
    }

    @Test
    fun `find a leech success`() {
        val leechId = createLeech()
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/leeches/{id}", leechId))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect {
                    it.response.contentAsString.jsonToObject(LeechRepresentation::class.java).run {
                        assertNotNull(id)
                        assertNotNull(createAt)
                        assertNotNull(status)
                        assertEquals(leechId, id)
                    }
                }
    }   
}
