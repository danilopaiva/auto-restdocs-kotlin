package br.com.leeches.controller

import br.com.leeches.representation.LeechRepresentation
import br.com.leeches.request.LeechRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/leeches")
class LeechesController {

    companion object {
       private val leeches = hashMapOf<String, LeechRepresentation>()
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun save(@RequestBody @Valid request: LeechRequest): LeechRepresentation {
        return saveLeech(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}", produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun find(@PathVariable("id") id: String): LeechRepresentation {
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