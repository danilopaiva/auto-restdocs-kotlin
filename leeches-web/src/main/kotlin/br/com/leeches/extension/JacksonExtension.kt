package br.com.leeches.extension

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

object JacksonExtension {

    val jacksonObjectMapper: ObjectMapper by lazy {
        ObjectMapper().registerModule(JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(KotlinModule())
    }
}

fun <T> String.jsonToArrayListObject(t: Class<T>): ArrayList<T> {
    val valueType = TypeFactory.defaultInstance().constructCollectionType(ArrayList::class.java, t)
    return JacksonExtension.jacksonObjectMapper.readValue(this, valueType)
}

fun <T> String.jsonToMutableListObject(t: Class<T>): MutableList<T> {
    val valueType = TypeFactory.defaultInstance().constructCollectionType(MutableList::class.java, t)
    return JacksonExtension.jacksonObjectMapper.readValue(this, valueType)
}

fun <T> String.jsonToObject(t: Class<T>): T =
        JacksonExtension.jacksonObjectMapper.readValue(this, t)

fun <T> T.objectToJson(): String =
        JacksonExtension.jacksonObjectMapper.writeValueAsString(this)

fun <T> T.toJsonNode(): JsonNode =
        JacksonExtension.jacksonObjectMapper.convertValue(this, JsonNode::class.java)

fun <T, K> String.jsonToMap(t: Class<T>, k: Class<K>): Map<T?, K?> {
    val valueType = TypeFactory.defaultInstance().constructMapType(Map::class.java, t, k)
    return JacksonExtension.jacksonObjectMapper.readValue(this, valueType)
}
