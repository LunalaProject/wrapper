package com.gabriel.lunala.project.service

import com.gabriel.lunala.project.LunalaWrapper
import com.gabriel.lunala.project.entity.User
import com.gabriel.lunala.project.entity.UserCreateDTO
import com.gabriel.lunala.project.entity.UserUpdateDTO
import com.gabriel.lunala.project.util.applyHeaders
import io.ktor.client.request.*
import io.ktor.http.*

class UserService(private val wrapper: LunalaWrapper, private val route: String = "${wrapper.url}/users") {

    suspend fun create(create: UserCreateDTO) = wrapper.client.post<User>(route) {
        body = create
        applyHeaders(wrapper)
    }

    suspend fun retrieve(id: Long) = wrapper.client.get<User>("$route/$id") {
        applyHeaders(wrapper)
    }

    suspend fun update(id: Long, update: UserUpdateDTO): User = wrapper.client.put("$route/$id") {
        body = update
        applyHeaders(wrapper)
    }

    suspend fun delete(id: Long) = wrapper.client.delete<HttpStatusCode>("$route/$id") {
        applyHeaders(wrapper)
    }
}