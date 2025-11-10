package com.cyberstarege.ruratesx.data.remote

import com.cyberstarege.ruratesx.data.model.CbrDailyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CurrencyRemoteDataSourceKtor(
    private val httpClient: HttpClient
) : CurrencyRemoteDataSource {
    override suspend fun getCurrencies(): CbrDailyResponse {
        val cbrDailyResponse: CbrDailyResponse = httpClient
            .get("daily_json.js")
            .body()
        return cbrDailyResponse
    }
}