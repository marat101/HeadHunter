package ru.marat.api

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

interface AppHttpClient {
    suspend fun request(builder: HttpRequestBuilder.() -> Unit): HttpResponse
}