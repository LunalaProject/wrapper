package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.User
import com.gabriel.lunala.project.entity.UserCreateDTO
import com.gabriel.lunala.project.entity.UserUpdateDTO
import com.gabriel.lunala.project.util.Default
import com.gabriel.lunala.project.util.nullableCall
import io.ktor.client.request.*
import io.ktor.http.*

class UserService(private val wrapper: LunalaWrapper, private val endpoint: String = "${wrapper.url}/users") {

    suspend fun create(create: UserCreateDTO) = wrapper.client.post<User>(endpoint) {
        header(HttpHeaders.Authorization, wrapper.key)
        header(HttpHeaders.ContentType, ContentType.Application.Default)

        body = create
    }

    suspend fun retrieve(id: Long) = nullableCall<User>(wrapper.client, "$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header(HttpHeaders.ContentType, ContentType.Application.Default)
    }

    suspend fun update(id: Long, update: UserUpdateDTO): User = wrapper.client.put("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header(HttpHeaders.ContentType, ContentType.Application.Default)

        body = update
    }

    suspend fun delete(id: Long) = wrapper.client.delete<HttpStatusCode>("$endpoint/$id") {
        header(HttpHeaders.Authorization, wrapper.key)
        header(HttpHeaders.ContentType, ContentType.Application.Default)
    }
}