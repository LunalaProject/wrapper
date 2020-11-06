package com.gabriel.lunala.project

import io.ktor.client.*
import kotlinx.serialization.json.Json

class LunalaWrapper(
    val url: String,
    val key: String,
    val json: Json = Json.Default,
    val client: HttpClient
)