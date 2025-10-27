package com.cyberstarege.ruratesx.components.button

import com.cyberstarege.ruratesx.styles.BorderRadius
import com.cyberstarege.ruratesx.theme.ColorTheme
import com.cyberstarege.ruratesx.utils.lighten
import org.jetbrains.compose.web.css.*

object SButtonStyle : StyleSheet() {
    val base by style {
        padding(16.px)
        borderRadius(BorderRadius.roundedMd)
        border(
            width = 0.px,
            style = LineStyle.None,
            color = null
        )
        color(ColorTheme.onPrimary)
        backgroundColor(ColorTheme.primary)
        self + hover style {
            backgroundColor(ColorTheme.primary.lighten(10))
        }

        self + focus style {
            backgroundColor(ColorTheme.primary.lighten(20))
        }
    }
}