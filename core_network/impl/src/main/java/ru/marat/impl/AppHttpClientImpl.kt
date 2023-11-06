package ru.marat.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import ru.marat.api.AppHttpClient

class AppHttpClientImpl(private val client: HttpClient): AppHttpClient {
    override suspend fun request(builder: HttpRequestBuilder.() -> Unit): HttpResponse {
        return client.request(builder) // todo
    }
}