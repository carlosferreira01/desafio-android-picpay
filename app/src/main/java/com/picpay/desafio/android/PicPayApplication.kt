package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.feature.user.di.apiModule
import com.picpay.desafio.android.feature.user.di.userDataModule
import com.picpay.desafio.android.feature.user.di.userDomainModule
import com.picpay.desafio.android.feature.user.di.userUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication: Application() {

    private lateinit var mInstance: PicPayApplication

    override fun onCreate() {
        super.onCreate()

        mInstance = this

        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)

            modules(apiModule + userDataModule + userDomainModule + userUiModule)
        }
    }

}