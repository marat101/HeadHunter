package ru.marat.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.engine.android.AndroidEngineConfig

class HttpClient {
    val a = HttpClient(AndroidClientEngine(AndroidEngineConfig()))
}