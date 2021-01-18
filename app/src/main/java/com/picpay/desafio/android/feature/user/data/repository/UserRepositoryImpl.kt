package com.picpay.desafio.android.feature.user.data.repository

import com.picpay.desafio.android.commons.api.ApiClient
import com.picpay.desafio.android.commons.api.BaseCallback
import com.picpay.desafio.android.feature.user.data.api.PicPayService
import com.picpay.desafio.android.feature.user.domain.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(mClient: ApiClient) : UserRepository {

    private val mService = mClient.createService(PicPayService::class.java)

    override fun getUser(callback: BaseCallback<List<User>>) {
        mService.getUsers().enqueue(
            object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    callback.onError(t.message.toString())
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let { callback.onSuccess(it) }
                }
            })
    }
}