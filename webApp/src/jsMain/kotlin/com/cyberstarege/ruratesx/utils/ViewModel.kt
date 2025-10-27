package com.cyberstarege.ruratesx.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class ViewModel {
    val viewModelScope = CoroutineScope(Dispatchers.Default)
}