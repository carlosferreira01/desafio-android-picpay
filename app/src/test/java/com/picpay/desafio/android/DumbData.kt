package com.picpay.desafio.android

import com.picpay.desafio.android.feature.user.domain.model.User
import retrofit2.Call

object DumbData {

    fun getUser() = User(
        "image",
        "José Roberto",
        1,
        "@joserobeto"
    )

    fun getUserList() = mutableListOf(
        getUser(),
        getUser(),
        getUser()
    )
}