package com.cyberstarege.ruratesx.utils

import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.px

fun StyleScope.padding(vertical: CSSNumeric = 0.px, horizontal: CSSNumeric = 0.px) {
    property("padding", "$vertical $horizontal")
}