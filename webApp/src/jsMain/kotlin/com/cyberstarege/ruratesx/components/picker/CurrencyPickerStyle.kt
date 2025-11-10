package com.cyberstarege.ruratesx.components.picker

import com.cyberstarege.ruratesx.styles.BorderRadius
import com.cyberstarege.ruratesx.theme.ColorTheme
import org.jetbrains.compose.web.css.*

object CurrencyPickerStyle : StyleSheet() {

    val base by style {
        position(Position.Relative)
    }

    val dropdown by style {
        position(Position.Absolute)
        marginTop(8.px)
        backgroundColor(ColorTheme.surfaceContainerLow)
        color(ColorTheme.onSurface)
        borderRadius(BorderRadius.roundedMd)
        border(1.px, LineStyle.Solid, ColorTheme.outline)
        maxHeight(360.px)
        overflowY("scroll")
        overflowX("hidden")
    }

    val option by style {
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        gap(12.px)
        color(ColorTheme.onSurface)
        padding(10.px, 12.px)
        cursor("pointer")

        self + hover style {
            backgroundColor(ColorTheme.inverseSurface)
            color(Color.black)
        }
    }

    val code by style {
        fontWeight(700)
        minWidth(44.px)
        backgroundColor(Color.transparent)

    }

    val name by style {
        backgroundColor(Color.transparent)
    }
}
