package com.cyberstarege.ruratesx.components.icon

import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor

object MaterialIconButtonStyle: StyleSheet() {
    val icon by style {
        backgroundColor(Color.transparent)
        color(Color.white)
        cursor("pointer")
    }
}