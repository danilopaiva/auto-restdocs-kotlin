package br.com.leechers

import br.com.leechers.config.ApplicationTestConfig
import br.com.leechers.extension.jsonToObject
import br.com.leechers.extension.objectToJson
import br.com.leechers.representation.LeecherRepresentation
import br.com.leechers.request.LeechersRequest
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(ApplicationTestConfig::class)])
abstract class ControllerBaseTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build()
    }

    fun createLeecher(leecher: LeechersRequest = getLeechersRequest()) =
            this.mockMvc.perform(post("/leechers")
                    .content(leecher.objectToJson())
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn()
                    .response.contentAsString.jsonToObject(LeecherRepresentation::class.java).id

    fun getLeechersRequest() =
            LeechersRequest(
                    name = "Luiz Inacio Lula da Silva",
                    description = "ex presidente",
                    stolenMoney = 1000000.00
            )
}