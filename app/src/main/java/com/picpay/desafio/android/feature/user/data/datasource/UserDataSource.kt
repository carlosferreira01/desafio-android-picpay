package com.picpay.desafio.android.feature.user.data.datasource

import com.picpay.desafio.android.feature.user.domain.model.User

interface UserDataSource {

    fun saveUsers(mUserList: List<User>): Boolean
    fun deleteUsers(): Boolean
    fun getUsersLocal(): List<User>
    fun statusUser(): Boolean

}