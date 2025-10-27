package com.cyberstarege.ruratesx.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.ContentBuilder
import org.w3c.dom.HTMLDivElement

@Composable
fun MainContent(
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Column(
        attrs = {
            style {
                marginLeft(200.px)
                marginRight(200.px)
            }
        },
        content = content
    )
}