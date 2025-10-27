package com.cyberstarege.ruratesx.utils

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleScope

fun StyleScope.boxShadow(
    offsetX: CSSNumeric,
    offsetY: CSSNumeric,
    blurRadius: CSSNumeric,
    spreadRadius: CSSNumeric,
    color: CSSColorValue
) {
    property("box-shadow", "$offsetX $offsetY $blurRadius $spreadRadius $color")
}
