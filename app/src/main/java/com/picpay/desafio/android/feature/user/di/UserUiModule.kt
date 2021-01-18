package com.picpay.desafio.android.feature.user.di

import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenter
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenterImpl
import org.koin.dsl.module

val userPresenterModule = module {

    factory<UserPresenter> {
        UserPresenterImpl(mView = get())
    }
}

val userUiModule = listOf(userPresenterModule)