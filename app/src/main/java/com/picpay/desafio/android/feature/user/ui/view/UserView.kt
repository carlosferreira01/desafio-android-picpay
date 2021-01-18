package com.picpay.desafio.android.feature.user.ui.view

import com.picpay.desafio.android.feature.user.domain.model.User

interface UserView {
    fun showUsers(list: List<User>)
    fun showErrorMessage(message: String)
    fun showErrorNetwork()
}