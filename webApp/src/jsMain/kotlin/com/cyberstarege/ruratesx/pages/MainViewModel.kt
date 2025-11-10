package com.cyberstarege.ruratesx.pages

import com.cyberstarege.ruratesx.domain.model.Currency
import com.cyberstarege.ruratesx.domain.repository.CurrencyRepository
import com.cyberstarege.ruratesx.utils.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {
    private val currencyRepository: CurrencyRepository by inject()
    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    fun getCurrencyList() = viewModelScope.launch {
        runCatching { currencyRepository.getCurrencies() }
            .onSuccess { rates ->
                _mainUiState.update { mainUiState ->
                    val crossRate = crossRate(
                        fromCode = mainUiState.fromCurrencyCode,
                        toCode = mainUiState.toCurrencyCode,
                        rates = rates
                    )
                    val result = calculate(
                        amount = mainUiState.amount,
                        fromCode = mainUiState.fromCurrencyCode,
                        toCode = mainUiState.toCurrencyCode,
                        rates = rates
                    )
                    mainUiState.copy(
                        currencies = rates,
                        crossRate = crossRate,
                        result = result
                    )
                }
            }
            .onFailure {
                _mainUiState.update { mainUiState ->
                    mainUiState.copy(currencies = emptyList())
                }
            }
    }

    fun updateAmount(newAmount: String) = _mainUiState.update { mainUiState ->
        val sanitized = sanitizeNumber(newAmount)
        val result = calculate(
            amount = sanitized,
            fromCode = mainUiState.fromCurrencyCode,
            toCode = mainUiState.toCurrencyCode,
            rates = mainUiState.currencies
        )
        mainUiState.copy(
            amount = sanitized,
            result = result
        )
    }

    fun selectFrom(code: String) = _mainUiState.update { mainUiState ->
        val crossRate = crossRate(
            fromCode = code,
            toCode = mainUiState.toCurrencyCode,
            rates = mainUiState.currencies
        )
        val result = calculate(
            amount = mainUiState.amount,
            fromCode = code,
            toCode = mainUiState.toCurrencyCode,
            rates = mainUiState.currencies
        )
        mainUiState.copy(
            fromCurrencyCode = code,
            crossRate = crossRate,
            result = result
        )
    }

    fun selectTo(code: String) = _mainUiState.update { mainUiState ->
        val crossRate = crossRate(
            fromCode = mainUiState.fromCurrencyCode,
            toCode = code,
            rates = mainUiState.currencies
        )
        val result = calculate(
            amount = mainUiState.amount,
            fromCode = mainUiState.fromCurrencyCode,
            toCode = code,
            rates = mainUiState.currencies
        )
        mainUiState.copy(
            toCurrencyCode = code,
            crossRate = crossRate,
            result = result
        )
    }

    fun swap() {
        _mainUiState.update { mainUiState->
            val crossRate = crossRate(
                fromCode = mainUiState.toCurrencyCode,
                toCode = mainUiState.fromCurrencyCode,
                rates = mainUiState.currencies
            )
            val result = calculate(
                amount = mainUiState.amount,
                fromCode = mainUiState.toCurrencyCode,
                toCode = mainUiState.fromCurrencyCode,
                rates = mainUiState.currencies
            )
            mainUiState.copy(
                fromCurrencyCode = mainUiState.toCurrencyCode,
                toCurrencyCode = mainUiState.fromCurrencyCode,
                crossRate = crossRate,
                result = result
            )
        }
    }

    private fun sanitizeNumber(text: String): String =
        text.replace(',', '.').replace(Regex("(\\..*)\\."), "$1") // одна точка

    private fun perUnitRub(code: String, rates: List<Currency>): Double {
        if (code.uppercase() == "RUB") return 1.0
        val c = rates.firstOrNull { it.charCode == code.uppercase() } ?: return 0.0
        if (c.nominal == 0) return 0.0
        return c.value / c.nominal.toDouble()
    }

    private fun crossRate(fromCode: String, toCode: String, rates: List<Currency>): Double {
        val rFrom = perUnitRub(fromCode, rates)
        val rTo = perUnitRub(toCode, rates)
        if (rFrom == 0.0 || rTo == 0.0) return 0.0
        return rFrom / rTo
    }

    private fun convert(amount: Double, from: String, to: String, rates: List<Currency>): Double {
        val rFrom = perUnitRub(from, rates)
        val rTo = perUnitRub(to, rates)
        if (rFrom == 0.0 || rTo == 0.0) return 0.0
        return amount * (rFrom / rTo)
    }

    private fun calculate(amount: String, fromCode: String, toCode: String, rates: List<Currency>): Double {
        val amount = amount.toDoubleOrNull() ?: 0.0
        val result = convert(amount, fromCode, toCode, rates)
        return result
    }
}