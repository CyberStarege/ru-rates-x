package com.cyberstarege.ruratesx.components.card

import com.cyberstarege.ruratesx.styles.BorderRadius
import com.cyberstarege.ruratesx.theme.ColorTheme
import org.jetbrains.compose.web.css.*

object CardStyle: StyleSheet() {
    val base by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        borderRadius(BorderRadius.roundedMd)
        color(ColorTheme.onSurface)
        backgroundColor(ColorTheme.surfaceContainerLow)
        padding(16.px)
        width(100.percent)
    }
}