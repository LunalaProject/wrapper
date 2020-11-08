package com.gabriel.lunala.project.util

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

suspend inline fun <reified T> nullableCall(client: HttpClient, route: String, builder: HttpRequestBuilder.() -> Unit) = try {
    client.get<T>(route, block = builder)
} catch (exception: ClientRequestException) {
    null
}