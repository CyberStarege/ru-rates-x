package com.cyberstarege.ruratesx.pages

import androidx.compose.runtime.*
import com.cyberstarege.ruratesx.components.Column
import com.cyberstarege.ruratesx.components.Row
import com.cyberstarege.ruratesx.components.badges.DailyChangeBadge
import com.cyberstarege.ruratesx.components.card.Card
import com.cyberstarege.ruratesx.components.icon.MaterialIconButton
import com.cyberstarege.ruratesx.components.inputs.STextInput
import com.cyberstarege.ruratesx.components.picker.CurrencyPicker
import com.cyberstarege.ruratesx.domain.model.Currency
import com.cyberstarege.ruratesx.theme.ColorTheme
import com.cyberstarege.ruratesx.utils.padding
import com.cyberstarege.ruratesx.utils.twoDecimalsString
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text


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
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
                gap(24.px)
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
        CurrenciesList(currencies = mainUiState.currencies)
    }
}

@Composable
fun CurrenciesList(
    currencies: List<Currency>
) {
    Card {
        Span(
            attrs = {
                style {
                    padding(horizontal = 2.px)
                    fontSize(24.px)
                    fontWeight(800)
                    backgroundColor(Color.transparent)
                    color(ColorTheme.onSurface)
                }
            }
        ) {
            Text("Курсы валют ЦБ РФ")
        }
        currencies.forEach { currency ->
            if (currency.charCode != "RUB") {
                CurrencyInfoRow(currency = currency)
            }
        }
    }
}

@Composable
fun CurrencyInfoRow(
    currency: Currency,
) {
    Row(
        attrs = {
            style {
                display(DisplayStyle.Grid)
                gap(16.px)
                padding(vertical = 8.px)
                alignItems(AlignItems.Center)
                property("grid-template-columns", "1fr 120px 120px 90px")
                backgroundColor(Color.transparent)
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
            Text(currency.name)
        }
        Span(
            attrs = {
                style {
                    backgroundColor(Color.transparent)
                    color(ColorTheme.onSurface)
                }
            }
        ) {
            Text("${currency.nominal} ${currency.charCode}")
        }
        Span(
            attrs = {
                style {
                    backgroundColor(Color.transparent)
                    color(ColorTheme.onSurface)
                }
            }
        ) {
            Text("${currency.value}")
        }
        DailyChangeBadge(currency.value - currency.previous)
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
                    width(100.percent)
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
