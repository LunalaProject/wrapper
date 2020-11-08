package com.gabriel.lunala.project.entity

import com.gabriel.lunala.project.util.PremiumType
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val ship: Int,
    val coins: Long,
    val equipment: Int,
    val crew: Int,
    val planet: String,
    val galaxy: String,
    val premium: PremiumType
)

@Serializable
data class UserCreateDTO(
    val id: Long,
    val ship: Int,
    val coins: Long,
    val equipment: Int,
    val crew: Int,
    val planet: String,
    val galaxy: String,
    val premium: PremiumType
)

@Serializable
data class UserUpdateDTO(
    val ship: Int? = null,
    val coins: Long? = null,
    val equipment: Int? = null,
    val crew: Int? = null,
    val planet: String? = null,
    val galaxy: String? = null,
    val premium: PremiumType? = null
)