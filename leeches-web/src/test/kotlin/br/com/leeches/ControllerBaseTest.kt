package br.com.leeches

import br.com.leeches.config.ApplicationTestConfig
import br.com.leeches.extension.jsonToObject
import br.com.leeches.extension.objectToJson
import br.com.leeches.representation.LeechRepresentation
import br.com.leeches.request.LeechRequest
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
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

    @Rule
    @JvmField
    var restDocumentation = JUnitRestDocumentation()

    lateinit var documentHandler: RestDocumentationResultHandler

    @Before
    fun setUp() {
        this.documentHandler = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()))

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply<DefaultMockMvcBuilder>(documentationConfiguration(this.restDocumentation))
                .alwaysDo<DefaultMockMvcBuilder>(this.documentHandler)
                .build()
    }

    fun createLeech(leech: LeechRequest = getLeechRequest()) =
            this.mockMvc.perform(post("/leeches")
                    .content(leech.objectToJson())
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn()
                    .response.contentAsString.jsonToObject(LeechRepresentation::class.java).id

    fun getLeechRequest() =
            LeechRequest(
                    name = "Luiz Inacio Lula da Silva",
                    description = "ex presidente",
                    stolenMoney = 1000000.00
            )
}