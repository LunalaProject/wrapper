package com.gabriel.lunala.project.util

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import io.ktor.util.*

@KtorExperimentalAPI
fun HttpClientConfig<*>.prepareClient() = apply {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
        acceptContentTypes = acceptContentTypes + ContentType("application", "json")
    }
}