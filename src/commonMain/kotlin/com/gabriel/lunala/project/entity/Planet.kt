package com.gabriel.lunala.project.entity

import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val distance: Long,
    val security: Float,
    val owner: Long?,
    val galaxy: String,
    val visited: Boolean,
)

@Serializable
data class PlanetCreateDTO(
    val name: String? = null,
    val distance: Long? = null,
    val security: Float? = null,
    val owner: Long? = null,
    val galaxy: String? = null,
    val visited: Boolean = false
)

@Serializable
data class PlanetUpdateDTO(
    val distance: Long? = null,
    val security: Float? = null,
    val owner: Long? = null,
    val galaxy: String? = null,
    val visited: Boolean? = null
)