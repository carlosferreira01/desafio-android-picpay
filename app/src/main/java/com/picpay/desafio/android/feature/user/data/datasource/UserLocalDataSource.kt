package com.picpay.desafio.android.feature.user.data.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.picpay.desafio.android.commons.utils.Constants
import com.picpay.desafio.android.feature.user.domain.model.User

class UserLocalDataSource(private val mPreferences: SharedPreferences): UserDataSource {

    override fun saveUsers(mUserList: List<User>): Boolean {
        val data = Gson().toJson(mUserList)
        return mPreferences
            .edit()
            .putString(Constants.USER_LOCAL_DATA_SOURCE, data)
            .commit()
    }

    override fun getUsersLocal(): List<User> {
        return Gson().fromJson(
            mPreferences.getString(Constants.USER_LOCAL_DATA_SOURCE, ""),
            Array<User>::class.java
        ).toList()
    }

    override fun statusUser(): Boolean {
        val response = mPreferences.getString(Constants.USER_LOCAL_DATA_SOURCE, "")

        return !response.isNullOrEmpty()
    }

    override fun deleteUsers(): Boolean {
        return mPreferences
            .edit()
            .remove(Constants.USER_LOCAL_DATA_SOURCE)
            .commit()
    }
}