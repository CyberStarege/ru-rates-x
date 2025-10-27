package com.cyberstarege.ruratesx.utils

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import kotlin.math.roundToInt

fun CSSColorValue.darken(percent: Int): CSSColorValue {
    val hex = this.toString().removePrefix("#")

    if (hex.length != 6) {
        throw IllegalArgumentException("Incorrect color")
    }

    val pc = percent.coerceIn(0, 100)
    val factor = 1 - (pc / 100.0)

    val r = (hex.substring(0, 2).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val g = (hex.substring(2, 4).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val b = (hex.substring(4, 6).toInt(16) * factor).roundToInt().coerceIn(0, 255)

    return rgb(r, g, b)
}

fun CSSColorValue.lighten(percent: Int): CSSColorValue {
    val hex = this.toString().removePrefix("#")

    if (hex.length != 6) {
        throw IllegalArgumentException("Incorrect color")
    }

    val pc = percent.coerceIn(0, 100)
    val factor = 1 + (pc / 100.0)

    val r = (hex.substring(0, 2).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val g = (hex.substring(2, 4).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val b = (hex.substring(4, 6).toInt(16) * factor).roundToInt().coerceIn(0, 255)

    return rgb(r, g, b)
}