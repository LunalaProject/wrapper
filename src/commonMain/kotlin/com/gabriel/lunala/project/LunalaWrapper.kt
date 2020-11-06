package com.gabriel.lunala.project

import io.ktor.client.*

class LunalaWrapper(
    val url: String,
    val key: String,
    val client: HttpClient
)