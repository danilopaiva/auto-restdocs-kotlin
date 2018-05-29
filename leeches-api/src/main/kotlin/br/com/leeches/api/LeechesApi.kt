package br.com.leeches.api

import br.com.leeches.representation.LeechRepresentation
import br.com.leeches.request.LeechRequest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Leeches API
 */
@RequestMapping("/leeches")
interface LeechesApi {

    /**
     * Save leech
     */
    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun save(@RequestBody @Valid request: LeechRequest): LeechRepresentation

    /**
     * @param id ID of the leech.
     */
    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping("/{id}", produces = [APPLICATION_JSON_VALUE])
    fun find(@PathVariable("id") id: String): LeechRepresentation
}