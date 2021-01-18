package com.picpay.desafio.android.feature.user.data.repository

import com.picpay.desafio.android.commons.api.BaseCallback
import com.picpay.desafio.android.feature.user.domain.model.User

interface UserRepository {

    fun getUser(callback: BaseCallback<List<User>>)
}