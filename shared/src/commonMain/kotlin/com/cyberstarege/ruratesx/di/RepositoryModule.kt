package com.cyberstarege.ruratesx.di

import com.cyberstarege.ruratesx.data.repository.CurrencyRepositoryImpl
import com.cyberstarege.ruratesx.domain.repository.CurrencyRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::CurrencyRepositoryImpl).bind<CurrencyRepository>()
}