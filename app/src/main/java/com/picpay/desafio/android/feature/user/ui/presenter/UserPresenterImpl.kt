package com.picpay.desafio.android.feature.user.ui.presenter

import com.picpay.desafio.android.feature.user.domain.model.User
import com.picpay.desafio.android.feature.user.ui.view.UserView

class UserPresenterImpl(private val mView: UserView) : UserPresenter {
    override fun showUsers(list: List<User>) {
        mView.showUsers(list)
    }

    override fun showErrorMessage(message: String) {
        mView.showErrorMessage(message)
    }

    override fun showErrorNetwork() {
        mView.showErrorNetwork()
    }
}