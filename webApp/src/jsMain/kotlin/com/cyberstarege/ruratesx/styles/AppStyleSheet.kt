package com.cyberstarege.ruratesx.styles

import com.cyberstarege.ruratesx.theme.ColorTheme
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.fontFamily

object AppStylesheet : StyleSheet() {
    init {
        "*" style {
            backgroundColor(ColorTheme.surface)
            fontFamily("Montserrat", "sans-serif")
        }
    }
}