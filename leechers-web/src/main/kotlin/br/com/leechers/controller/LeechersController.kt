package br.com.leechers.controller

import br.com.leechers.representation.LeecherRepresentation
import br.com.leechers.request.LeechersRequest
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
@RequestMapping("/leechers")
class LeechersController {

    companion object {
        val leechers = hashMapOf<String, LeecherRepresentation>()
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun save(@RequestBody @Valid request: LeechersRequest): LeecherRepresentation {
        return saveLeecher(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}", produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun find(@PathVariable("id") id: String): LeecherRepresentation {
        return Optional.ofNullable(leechers[id])
                .orElseThrow {
                    Exception()
                }
    }

    private fun saveLeecher(request: LeechersRequest): LeecherRepresentation {
        val id = UUID.randomUUID().toString()
        leechers[id] = LeecherRepresentation(
                id = id,
                name = request.name,
                description = request.description,
                stolenMoney = request.stolenMoney,
                status = "SOLTO!",
                createAt = LocalDateTime.now()
        )
        return Optional.ofNullable(leechers[id])
                .orElseThrow {
                    Exception()
                }
    }
}