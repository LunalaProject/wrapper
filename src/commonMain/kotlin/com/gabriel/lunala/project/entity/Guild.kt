package com.gabriel.lunala.project.entity

import kotlinx.serialization.Serializable

@Serializable
data class Guild(
    val id: Long,
    val locale: String,
    val partner: Boolean
)

@Serializable
data class GuildCreateDTO(
    val id: Long,
    val locale: String,
    val partner: Boolean
)

@Serializable
data class GuildUpdateDTO(
    val locale: String? = null,
    val partner: Boolean? = null
)