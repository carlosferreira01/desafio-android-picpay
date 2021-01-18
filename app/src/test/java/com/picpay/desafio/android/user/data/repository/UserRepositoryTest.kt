package com.picpay.desafio.android.user.data.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.DumbData
import com.picpay.desafio.android.commons.api.ApiClient
import com.picpay.desafio.android.commons.api.BaseCallback
import com.picpay.desafio.android.feature.user.data.api.PicPayService
import com.picpay.desafio.android.feature.user.data.repository.UserRepository
import com.picpay.desafio.android.feature.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.feature.user.domain.model.User
import okhttp3.internal.EMPTY_RESPONSE
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response



class UserRepositoryTest {

    @Mock
    private val mClient = ApiClient()

    private lateinit var mRepository: UserRepository

    @Mock
    private var callback: BaseCallback<List<User>> = mock()

    @Before
    fun setup() {

        mRepository = UserRepositoryImpl(mClient)
    }

    @Test
    fun getUsers_ok() {
        mRepository.getUser(callback)

        Assertions.assertThat(mRepository.getUser(callback)).isEqualTo(Unit)

        val response = Response.success(DumbData.getUserList() as List<User>)

        val mockCall = mock<Call<List<User>>> {
            on { execute() } doReturn response
        }

        val mockApiService = mock<PicPayService> {
            on { getUsers() } doReturn mockCall
        }

        Mockito.`when`(mockApiService.getUsers()).thenReturn(mockCall)

    }

    @Test
    fun getUsers_error() {
        mRepository.getUser(callback)

        Assertions.assertThat(mRepository.getUser(callback)).isEqualTo(Unit)


        val mockCall = mock<Call<List<User>>> {
            on { execute() } doReturn Response.error(500, EMPTY_RESPONSE)
        }

        val mockApiService = mock<PicPayService> {
            on { getUsers() } doReturn mockCall
        }

        Mockito.`when`(mockApiService.getUsers()).thenReturn(mockCall)

    }
}