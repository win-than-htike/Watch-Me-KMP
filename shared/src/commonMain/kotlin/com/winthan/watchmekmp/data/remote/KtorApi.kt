package com.winthan.watchmekmp.data.remote

import com.winthan.watchmekmp.data.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.serialization.json.Json

internal abstract class KtorApi {

    val client = HttpClient {
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    isLenient = true
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(Constants.BASE_URL)
            path("3", path)
            parameter("api_key", Constants.API_KEY)
        }
    }

}