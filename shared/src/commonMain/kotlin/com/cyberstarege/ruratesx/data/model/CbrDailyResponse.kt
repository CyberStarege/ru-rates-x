package com.cyberstarege.ruratesx.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CbrDailyResponse(
    @SerialName("Date")
    val date: String,
    @SerialName("PreviousDate")
    val previousDate: String,
    @SerialName("PreviousURL")
    val previousUrl: String? = null,
    @SerialName("Timestamp")
    val timestamp: String,
    @SerialName("Valute")
    val valute: Map<String, CurrencyResponse>
)