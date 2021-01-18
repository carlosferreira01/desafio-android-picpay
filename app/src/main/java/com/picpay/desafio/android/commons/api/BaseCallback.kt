package com.picpay.desafio.android.commons.api

interface BaseCallback<T> {

    fun onSuccess(response: T)
    fun onError(error: String)
}