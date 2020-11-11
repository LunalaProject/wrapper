package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.Galaxy
import com.gabriel.lunala.project.util.nullableCall
import io.ktor.client.request.*
import io.ktor.http.*

class GalaxyService(val wrapper: LunalaWrapper, val endpoint: String = "${wrapper.url}/galaxy") {
    suspend fun retrieve(id: String): Galaxy? = nullableCall<Galaxy>(wrapper.client,"$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header("Content-Type", "application/json; charset=utf-8")
    }
}