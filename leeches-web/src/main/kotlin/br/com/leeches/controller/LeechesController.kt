package br.com.leeches.controller

import br.com.leeches.api.LeechesApi
import br.com.leeches.representation.LeechRepresentation
import br.com.leeches.request.LeechRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid

/**
 * Leeches controller
 */
@RestController
class LeechesController : LeechesApi {

    companion object {
        private val leeches = hashMapOf<String, LeechRepresentation>()
    }

    override fun save(@RequestBody @Valid request: LeechRequest): LeechRepresentation {
        return saveLeech(request)
    }

    override fun find(@PathVariable("id") id: String): LeechRepresentation {
        return Optional.ofNullable(leeches[id])
                .orElseThrow {
                    Exception()
                }
    }

    private fun saveLeech(request: LeechRequest): LeechRepresentation {
        val id = UUID.randomUUID().toString()
        leeches[id] = LeechRepresentation(
                id = id,
                name = request.name,
                description = request.description,
                stolenMoney = request.stolenMoney,
                status = "PRESO!",
                createAt = LocalDateTime.now()
        )
        return Optional.ofNullable(leeches[id])
                .orElseThrow {
                    Exception()
                }
    }
}
