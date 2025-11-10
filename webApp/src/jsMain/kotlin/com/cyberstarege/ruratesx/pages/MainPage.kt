package com.cyberstarege.ruratesx.pages

import androidx.compose.runtime.*
import com.cyberstarege.ruratesx.components.Column
import com.cyberstarege.ruratesx.components.Row
import com.cyberstarege.ruratesx.components.card.Card
import com.cyberstarege.ruratesx.components.icon.MaterialIconButton
import com.cyberstarege.ruratesx.components.inputs.STextInput
import com.cyberstarege.ruratesx.components.picker.CurrencyPicker
import com.cyberstarege.ruratesx.domain.model.Currency
import com.cyberstarege.ruratesx.theme.ColorTheme
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import kotlin.math.round


@Composable
fun MainPage(
    mainViewModel: MainViewModel
) {
    val mainUiState by mainViewModel.mainUiState.collectAsState()


    LaunchedEffect(Unit) {
        mainViewModel.getCurrencyList()
    }
    Column(
        attrs = {
            style {
                height(100.vh)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
            }
        }
    ) {
        Card {
            ConverterInput(
                amount = mainUiState.amount,
                fromCurrencyCode = mainUiState.fromCurrencyCode,
                toCurrencyCode = mainUiState.toCurrencyCode,
                onAmountChange = mainViewModel::updateAmount,
                currencies = mainUiState.currencies,
                onSwap = mainViewModel::swap,
                onSelectFromCurrency = {
                    mainViewModel.selectFrom(it.charCode)
                },
                onSelectToCurrency = {
                    mainViewModel.selectTo(it.charCode)
                }
            )
            ConverterInfo(
                fromCurrencyCode = mainUiState.fromCurrencyCode,
                toCurrencyCode = mainUiState.toCurrencyCode,
                crossRate = mainUiState.crossRate,
                amount = mainUiState.amount,
                result = mainUiState.result
            )
        }
    }
}


@Composable
fun ConverterInput(
    amount: String,
    fromCurrencyCode: String,
    toCurrencyCode: String,
    onAmountChange: (String) -> Unit,
    currencies: List<Currency>,
    onSwap: () -> Unit,
    onSelectFromCurrency: (Currency) -> Unit,
    onSelectToCurrency: (Currency) -> Unit

) {
    var openFrom by remember { mutableStateOf(false) }
    var openTo by remember { mutableStateOf(false) }
    Row(
        attrs = {
            style {
                gap(12.px)
                backgroundColor(Color.transparent)
                alignItems(AlignItems.Center)
            }
        }
    ) {
        STextInput(
            attrs = {
                style {
                    height(50.px)
                }
            },
            value = amount,
            onValueChange = onAmountChange
        )
        CurrencyPicker(
            selectedCode = fromCurrencyCode,
            currencies = currencies,
            showDropdownList = openFrom,
            onClick = {
                openFrom = !openFrom
            },
            onSelectCurrency = {
                onSelectFromCurrency(it)
                openFrom = !openFrom
            }
        )
        MaterialIconButton(
            classesText = "material-symbols-outlined",
            iconText = "swap_horiz",
            onClick = onSwap
        )
        CurrencyPicker(
            selectedCode = toCurrencyCode,
            currencies = currencies,
            showDropdownList = openTo,
            onClick = {
                openTo = !openTo
            },
            onSelectCurrency = {
                onSelectToCurrency(it)
                openTo = !openTo
            }
        )
    }
}

@Composable
fun ConverterInfo(
    fromCurrencyCode: String,
    toCurrencyCode: String,
    crossRate: Double,
    amount: String,
    result: Double,
) {
    Column(
        attrs = {
            style {
                backgroundColor(Color.transparent)
                paddingTop(12.px)
                gap(12.px)
            }
        }
    ) {
        Span(
            attrs = {
                style {
                    backgroundColor(Color.transparent)
                    color(ColorTheme.onSurface)
                }
            }
        ) {
            val crossRate = crossRate.twoDecimalsString()
            Text("1 $fromCurrencyCode = $crossRate $toCurrencyCode")
        }
        Span(
            attrs = {
                style {
                    backgroundColor(Color.transparent)
                    color(ColorTheme.onSurface)

                    fontWeight(700)
                    fontSize(18.px)
                }
            }
        ) {
            val result = result.twoDecimalsString()
            Text("$amount $fromCurrencyCode = $result $toCurrencyCode")
        }
    }
}

fun Double.twoDecimalsString(): String {
    val scaled = round(this * 100.0) / 100.0
    val s = scaled.toString()
    return if (s.contains('.')) {
        val parts = s.split('.')
        parts[0] + "." + parts[1].padEnd(2, '0').take(2)
    } else {
        "$s.00"
    }
}