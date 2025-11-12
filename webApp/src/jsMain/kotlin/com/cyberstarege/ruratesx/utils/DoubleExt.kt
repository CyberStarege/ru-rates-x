package com.cyberstarege.ruratesx.utils

import kotlin.math.round

fun Double.twoDecimalsString(): String {
    val scaled = round(this * 100.0) / 100.0
    val s = scaled.toString()
    return if (s.contains('.')) {
        val parts = s.split('.')
        parts[0] + "." + parts[1].padEnd(2, '0').take(2)
    } else {
        "$s.00"
    }
}