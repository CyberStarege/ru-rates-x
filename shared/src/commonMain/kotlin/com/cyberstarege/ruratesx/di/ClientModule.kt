package com.cyberstarege.ruratesx.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val CBR_API = "https://www.cbr-xml-daily.ru/"

val clientModule = module {
    single {
        HttpClient {
            val json = Json {
                ignoreUnknownKeys = true
            }
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Application.Json)
                register(ContentType.parse("application/javascript"), KotlinxSerializationConverter(json))
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("ktor.io")
                }
            }
            install(DefaultRequest)
            defaultRequest {
                url(CBR_API)
            }
        }
    }
}