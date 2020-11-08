package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Guild
import com.gabriel.lunala.project.entity.GuildCreateDTO
import com.gabriel.lunala.project.entity.GuildUpdateDTO
import io.ktor.client.request.*
import io.ktor.http.*

class GuildService(private val wrapper: LunalaWrapper, private val route: String = "${wrapper.url}/guilds") {

    suspend fun create(create: GuildCreateDTO) = wrapper.client.post<Guild>(route) {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = create
    }

    suspend fun retrieve(id: Long) = wrapper.client.get<Guild>("$route/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }

    suspend fun update(id: Long, update: GuildUpdateDTO): Guild = wrapper.client.put("$route/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = update
    }

    suspend fun delete(id: Long) = wrapper.client.delete<HttpStatusCode>("$route/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }
}