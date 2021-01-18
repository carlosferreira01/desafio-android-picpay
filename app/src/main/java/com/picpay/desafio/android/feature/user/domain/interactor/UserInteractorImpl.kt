package com.picpay.desafio.android.feature.user.domain.interactor

import android.content.Context
import android.widget.Toast
import com.picpay.desafio.android.R
import com.picpay.desafio.android.commons.api.BaseCallback
import com.picpay.desafio.android.commons.connection.InternetConnection
import com.picpay.desafio.android.feature.user.data.datasource.UserDataSource
import com.picpay.desafio.android.feature.user.data.repository.UserRepository
import com.picpay.desafio.android.feature.user.domain.model.User
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenter

class UserInteractorImpl(
    private val mUserRepository: UserRepository,
    private val mUserPresenter: UserPresenter,
    private val mUserDataSource: UserDataSource,
    private val mContext: Context
) : UserInteractor {

    override fun getUser() {

        val status = mUserDataSource.statusUser()

        if (!status) {
            if (InternetConnection.isInternetAvailable(mContext)) {
                mUserRepository.getUser(
                    object : BaseCallback<List<User>> {
                        override fun onSuccess(response: List<User>) {
                            mUserPresenter.showUsers(response)
                            mUserDataSource.saveUsers(response)
                        }

                        override fun onError(error: String) {
                            mUserPresenter.showErrorMessage(error)
                        }
                    }
                )
            } else {
                mUserPresenter.showErrorNetwork()
            }

        } else {
            if (InternetConnection.isInternetAvailable(mContext)) {
                mUserRepository.getUser(
                    object : BaseCallback<List<User>> {
                        override fun onSuccess(response: List<User>) {
                            mUserPresenter.showUsers(response)
                            mUserDataSource.deleteUsers()
                            mUserDataSource.saveUsers(response)
                        }

                        override fun onError(error: String) {
                            mUserPresenter.showErrorMessage(error)
                        }
                    }
                )
            } else {
                mUserPresenter.showUsers(mUserDataSource.getUsersLocal())
            }
        }

    }
}