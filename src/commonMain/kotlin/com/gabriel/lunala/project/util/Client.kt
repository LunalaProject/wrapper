package com.gabriel.lunala.project.util

import com.gabriel.lunala.project.LunalaWrapper
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.util.*

@KtorExperimentalAPI
fun LunalaWrapper.prepareClient() = client.config {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
        acceptContentTypes = acceptContentTypes + ContentType("application", "json")
    }
}.let { this }

// TODO: workaround
fun HttpRequestBuilder.applyHeaders(wrapper: LunalaWrapper) {
    header(HttpHeaders.Authorization, wrapper.key)
    header(HttpHeaders.ContentType, ContentType.Application.Json)
}