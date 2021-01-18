package com.picpay.desafio.android.feature.user.ui.presenter

import com.picpay.desafio.android.feature.user.domain.model.User

interface UserPresenter {
    fun showUsers(list: List<User>)
    fun showErrorMessage(message: String)
    fun showErrorNetwork()
}