package com.cyberstarege.ruratesx.domain.model

data class Currency(
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double
)
