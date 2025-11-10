package com.cyberstarege.ruratesx.components.icon

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

@Composable
fun MaterialIconButton(
    classesText: String,
    iconText: String,
    onClick: () -> Unit,
    attrs: AttrBuilderContext<HTMLElement>? = null
) {
    Style(MaterialIconButtonStyle)
    Span(
        attrs = {
            classes(MaterialIconButtonStyle.icon)
            classes(classesText)
            onClick { onClick() }
            attrs?.invoke(this)
        }
    ) {
        Text(iconText)
    }
}