package com.cyberstarege.ruratesx.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun Row(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
            }
            attrs?.invoke(this)
        },
        content = content
    )
}