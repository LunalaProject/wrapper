package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Guild
import com.gabriel.lunala.project.entity.GuildCreateDTO
import com.gabriel.lunala.project.entity.GuildUpdateDTO
import com.gabriel.lunala.project.util.nullableCall
import io.ktor.client.request.*
import io.ktor.http.*

class GuildService(private val wrapper: LunalaWrapper, private val endpoint: String = "${wrapper.url}/guilds") {

    suspend fun create(create: GuildCreateDTO) = wrapper.client.post<Guild>(endpoint) {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = create
    }

    suspend fun retrieve(id: Long) = nullableCall<Guild>(wrapper.client, "$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }

    suspend fun update(id: Long, update: GuildUpdateDTO): Guild = wrapper.client.put("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = update
    }

    suspend fun delete(id: Long) = wrapper.client.delete<HttpStatusCode>("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }
}