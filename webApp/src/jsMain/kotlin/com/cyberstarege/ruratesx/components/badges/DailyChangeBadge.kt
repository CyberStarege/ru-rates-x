package com.cyberstarege.ruratesx.components.badges

import androidx.compose.runtime.Composable
import com.cyberstarege.ruratesx.utils.twoDecimalsString
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun DailyChangeBadge(
    diff: Double
) {
    val trendStyle = when {
        diff > 0 -> DailyChangeBadgeStyle.positive
        diff < 0 -> DailyChangeBadgeStyle.negative
        else -> DailyChangeBadgeStyle.neutral
    }
    Style(DailyChangeBadgeStyle)
    Span(
        attrs = {
            classes(DailyChangeBadgeStyle.badge, trendStyle)
        }
    ) {
        Text(diff.twoDecimalsString())
    }
}


