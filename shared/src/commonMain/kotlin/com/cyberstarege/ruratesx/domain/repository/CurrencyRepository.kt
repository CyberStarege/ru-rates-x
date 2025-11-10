package com.cyberstarege.ruratesx.domain.repository

import com.cyberstarege.ruratesx.domain.model.Currency


interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>

    suspend fun getCurrency(code: String): Currency?
}