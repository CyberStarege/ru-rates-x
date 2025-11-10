package com.cyberstarege.ruratesx.data.repository

import com.cyberstarege.ruratesx.data.mappers.toCurrency
import com.cyberstarege.ruratesx.data.remote.CurrencyRemoteDataSource
import com.cyberstarege.ruratesx.domain.model.Currency
import com.cyberstarege.ruratesx.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {
    override suspend fun getCurrencies(): List<Currency> {
        val result = currencyRemoteDataSource.getCurrencies()
        val listOfCurrency = result.valute.values.map { it.toCurrency() }.withRubIfMissing()
        return listOfCurrency
    }

    override suspend fun getCurrency(code: String): Currency? {
        val result = currencyRemoteDataSource.getCurrencies()
        val listOfCurrency = result.valute.values.map { it.toCurrency() }.withRubIfMissing()
        val currency = listOfCurrency.firstOrNull{ it.charCode == code }
        return currency
    }
}

private fun List<Currency>.withRubIfMissing(): List<Currency> {
    val hasRub = any { it.charCode.contains("RUB") }
    if (hasRub) return this

    val rub = Currency(
        charCode = "RUB",
        nominal = 1,
        name = "Российский рубль",
        value = 1.0,
        previous = 1.0
    )
    return listOf(rub) + this
}
