package com.picpay.desafio.android.feature.user.di

import com.picpay.desafio.android.commons.api.ApiClient
import org.koin.dsl.module

val clientModule = module {

    ApiClient()

}

val apiModule = listOf(clientModule)