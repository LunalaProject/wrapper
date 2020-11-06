package com.gabriel.lunala.project.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Long, val coins: Long, val planet: String)

@Serializable
data class UserCreateDTO(val id: Long, val coins: Long, val planet: String)

@Serializable
data class UserUpdateDTO(val coins: Long?, val planet: String?)
