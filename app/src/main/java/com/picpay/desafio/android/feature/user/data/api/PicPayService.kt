package com.picpay.desafio.android.feature.user.data.api

import com.picpay.desafio.android.feature.user.domain.model.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}