package com.cyberstarege.ruratesx.data.remote

import com.cyberstarege.ruratesx.data.model.CbrDailyResponse

interface CurrencyRemoteDataSource {
    suspend fun getCurrencies(): CbrDailyResponse
}