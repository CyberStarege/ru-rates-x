package com.cyberstarege.ruratesx.data.mappers

import com.cyberstarege.ruratesx.data.model.CurrencyResponse
import com.cyberstarege.ruratesx.domain.model.Currency

fun CurrencyResponse.toCurrency(): Currency {
    return Currency(
        charCode = charCode,
        nominal = nominal,
        name = name,
        value = value,
        previous = previous
    )
}
