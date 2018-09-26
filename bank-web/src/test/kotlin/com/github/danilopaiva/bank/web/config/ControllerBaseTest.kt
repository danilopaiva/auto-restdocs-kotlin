package com.github.danilopaiva.bank.web.config

import capital.scalable.restdocs.AutoDocumentation
import capital.scalable.restdocs.AutoDocumentation.description
import capital.scalable.restdocs.AutoDocumentation.methodAndPath
import capital.scalable.restdocs.AutoDocumentation.pathParameters
import capital.scalable.restdocs.AutoDocumentation.requestFields
import capital.scalable.restdocs.AutoDocumentation.requestParameters
import capital.scalable.restdocs.AutoDocumentation.responseFields
import capital.scalable.restdocs.SnippetRegistry
import capital.scalable.restdocs.jackson.JacksonResultHandlers.prepareJackson
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors.limitJsonArrayLength
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors.replaceBinaryContent
import capital.scalable.restdocs.section.SectionSnippet
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.danilopaiva.bank.api.request.CreateCustomerRequest
import com.github.danilopaiva.bank.api.request.DocumentRequest
import com.github.danilopaiva.bank.api.request.UpdateCustomerRequest
import com.github.danilopaiva.bank.api.response.CustomerResponse
import com.github.danilopaiva.bank.domain.helper.jsonToObject
import com.github.danilopaiva.bank.domain.helper.objectToJson
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.cli.CliDocumentation
import org.springframework.restdocs.http.HttpDocumentation.httpRequest
import org.springframework.restdocs.http.HttpDocumentation.httpResponse
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
import java.util.*


@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(ApplicationTestConfig::class)])
abstract class ControllerBaseTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    @get:Rule
    var restDocumentation = JUnitRestDocumentation()

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    @Throws(Exception::class)
    fun setUp() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .alwaysDo<DefaultMockMvcBuilder>(prepareJackson(objectMapper))
            .alwaysDo<DefaultMockMvcBuilder>(commonDocumentation())
            .apply<DefaultMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .uris()
                    .and().snippets()
                    .withDefaults(
                        CliDocumentation.curlRequest(),
                        httpRequest(),
                        httpResponse(),
                        requestFields(),
                        responseFields(),
                        pathParameters(),
                        requestParameters(),
                        description(),
                        methodAndPath(),
                        buildSection()
                    )
            )
            .build()
    }

    private fun commonDocumentation(): RestDocumentationResultHandler {
        return document(
            "{class-name}/{method-name}",
            preprocessRequest(),
            preprocessResponse(
                replaceBinaryContent(),
                limitJsonArrayLength(objectMapper),
                prettyPrint()
            )
        )
    }

    private fun buildSection(): SectionSnippet {
        return AutoDocumentation.sectionBuilder()
            .snippetNames(
                SnippetRegistry.PATH_PARAMETERS,
                SnippetRegistry.HTTP_REQUEST,
                SnippetRegistry.REQUEST_PARAMETERS,
                SnippetRegistry.REQUEST_FIELDS,
                SnippetRegistry.HTTP_RESPONSE,
                SnippetRegistry.RESPONSE_FIELDS
            )
            .skipEmpty(true)
            .build()
    }


    fun createCustomer(customer: CreateCustomerRequest = dummyCreateCustomerRequest()) =
        this.mockMvc.perform(
            post("/customers")
                .content(customer.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn()
            .response.contentAsString.jsonToObject(CustomerResponse::class.java).id

    fun dummyCreateCustomerRequest() =
        CreateCustomerRequest(
            name = "Danilo Paiva",
            email = "danilopaivasilva@gmail.com",
            document = DocumentRequest(
                type = "IDENTITY",
                number = randomUUID()
            )
        )

    fun dummyUpdateCustomerRequest() =
        UpdateCustomerRequest(
            name = "A A",
            email = "a@a.com",
            document = DocumentRequest(
                type = "IDENTITY",
                number = randomUUID()
            )
        )

    private fun randomUUID() =
        UUID.randomUUID().toString()
}
