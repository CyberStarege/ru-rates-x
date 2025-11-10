package com.cyberstarege.ruratesx.components.picker

import androidx.compose.runtime.Composable
import com.cyberstarege.ruratesx.components.Row
import com.cyberstarege.ruratesx.components.button.SButton
import com.cyberstarege.ruratesx.domain.model.Currency
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun CurrencyPicker(
    selectedCode: String,
    showDropdownList: Boolean,
    currencies: List<Currency>,
    onClick: () -> Unit,
    onSelectCurrency: (Currency) -> Unit
) {
    Style(CurrencyPickerStyle)
    Div(
        attrs = {
            classes(CurrencyPickerStyle.base)
        }
    ) {
        SButton(
            attrs = {
                style {
                    height(50.px)
                    fontWeight(700)
                }
            },
            action = onClick
        ) {
            Text(selectedCode)
        }
        if (showDropdownList) {
            Div(
                attrs = {
                    classes(CurrencyPickerStyle.dropdown)
                }
            ) {
                currencies.forEach { currency ->
                    Row(
                        attrs = {
                            classes(CurrencyPickerStyle.option)
                            onClick {
                                onSelectCurrency(currency)
                            }
                        }
                    ) {
                        Div({ classes(CurrencyPickerStyle.code) }) {
                            Text(currency.charCode)
                        }
                        Div({ classes(CurrencyPickerStyle.name) }) {
                            Text(currency.name)
                        }
                    }
                }
            }
        }
    }
}