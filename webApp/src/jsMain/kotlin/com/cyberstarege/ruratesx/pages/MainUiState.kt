package com.cyberstarege.ruratesx.pages

import com.cyberstarege.ruratesx.domain.model.Currency

data class MainUiState(
    val currencies: List<Currency> = emptyList(),
    val amount: String = "1.00",
    val fromCurrencyCode: String = "USD",
    val toCurrencyCode: String = "RUB",
    val crossRate: Double = 0.0,
    val result: Double = 0.0
)
