package com.picpay.desafio.android.feature.user.di

import com.picpay.desafio.android.feature.user.data.repository.UserRepository
import com.picpay.desafio.android.feature.user.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val userRepositoryModule = module {

    factory<UserRepository> {
        UserRepositoryImpl(mClient = get())
    }
}

val userDataModule = listOf(userRepositoryModule)