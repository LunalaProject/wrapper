package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.User
import com.gabriel.lunala.project.entity.UserCreateDTO
import com.gabriel.lunala.project.entity.UserUpdateDTO
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserService(private val wrapper: LunalaWrapper, private val route: String = "${wrapper.url}/users") {

    private val contentType = "application/json;charset=utf-8"

    suspend fun create(create: UserCreateDTO) = wrapper.client.post<User>(route) {
        headers.append("Content-Type", contentType)
        headers.append(HttpHeaders.Authorization, wrapper.key)

        body = create
    }

    suspend fun retrieve(id: Long) = wrapper.client.get<User>("$route/$id") {
        headers.append("Content-Type", contentType)
        headers.append(HttpHeaders.Authorization, wrapper.key)
    }

    suspend fun update(id: Long, update: UserUpdateDTO): User = wrapper.client.put("$route/$id") {
        headers.append("Content-Type", contentType)
        headers.append(HttpHeaders.Authorization, wrapper.key)

        body = update
    }

    suspend fun delete(id: Long) = wrapper.client.delete<HttpStatusCode>("$route/$id") {
        headers.append("Content-Type", contentType)
        headers.append(HttpHeaders.Authorization, wrapper.key)
    }

}