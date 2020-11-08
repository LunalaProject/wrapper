package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Planet
import com.gabriel.lunala.project.entity.PlanetCreateDTO
import com.gabriel.lunala.project.entity.PlanetUpdateDTO
import com.gabriel.lunala.project.util.nullableCall
import io.ktor.client.request.*
import io.ktor.http.*

class PlanetService(private val wrapper: LunalaWrapper, private val endpoint: String = "${wrapper.url}/planets") {

    suspend fun create(create: PlanetCreateDTO) = wrapper.client.post<Planet>(endpoint) {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = create
    }

    suspend fun retrieve(id: String) = nullableCall<Planet>(wrapper.client, "$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }

    suspend fun update(id: String, update: PlanetUpdateDTO): Planet = wrapper.client.put("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")

        body = update
    }

    suspend fun delete(id: String) = wrapper.client.delete<HttpStatusCode>("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }
}