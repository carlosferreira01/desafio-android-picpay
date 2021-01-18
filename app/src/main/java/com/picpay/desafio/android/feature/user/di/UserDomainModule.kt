package com.picpay.desafio.android.feature.user.di

import com.picpay.desafio.android.feature.user.domain.interactor.UserInteractor
import com.picpay.desafio.android.feature.user.domain.interactor.UserInteractorImpl
import org.koin.dsl.module

val userInteractorModule = module {

    factory<UserInteractor> {
        UserInteractorImpl(mUserPresenter =  get(), mUserRepository = get(), mContext = get(), mUserDataSource = get())
    }
}

val userDomainModule = listOf(userInteractorModule)