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
                marginTop(100.px)
                marginLeft(450.px)
                marginRight(450.px)
                marginBottom(100.px)
            }
        },
        content = content
    )
}