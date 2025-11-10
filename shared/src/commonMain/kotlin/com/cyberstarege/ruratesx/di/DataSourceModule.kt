package com.cyberstarege.ruratesx.di

import com.cyberstarege.ruratesx.data.remote.CurrencyRemoteDataSource
import com.cyberstarege.ruratesx.data.remote.CurrencyRemoteDataSourceKtor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::CurrencyRemoteDataSourceKtor).bind<CurrencyRemoteDataSource>()
}