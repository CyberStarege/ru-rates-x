package com.cyberstarege.ruratesx.components.badges

import com.cyberstarege.ruratesx.styles.BorderRadius
import com.cyberstarege.ruratesx.utils.padding
import org.jetbrains.compose.web.css.*


object DailyChangeBadgeStyle: StyleSheet() {
    val badge by style {
        borderRadius(BorderRadius.roundedMd)
        padding(vertical = 4.px, horizontal = 8.px)
        fontSize(12.px)
        fontWeight(800)
        textAlign("center")
    }

    val positive by style {
        backgroundColor(Color("#22C55E"))
        color(Color.white)
    }

    val negative by style {
        backgroundColor(Color("#EF4444"))
        color(Color.white)
    }

    val neutral by style {
        backgroundColor(Color("#94A3B8"))
        color(Color.white)
    }
}