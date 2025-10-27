package com.cyberstarege.ruratesx.components.button

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.ContentBuilder
import org.w3c.dom.HTMLButtonElement

@Composable
fun SButton(
    action: () -> Unit,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) {

    Style(SButtonStyle)
    Button(
        attrs = {
            classes(SButtonStyle.base)
            onClick {
                action()
            }
            attrs?.invoke(this)
        },
        content = content
    )
}