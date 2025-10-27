package com.cyberstarege.ruratesx.components.card

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement


@Composable
fun Card(
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: @Composable () -> Unit
) {
    Style(CardStyle)
    Div(
        attrs = {
            classes(CardStyle.base)
            attrs?.invoke(this)
        }
    ) {
        content()
    }
}
