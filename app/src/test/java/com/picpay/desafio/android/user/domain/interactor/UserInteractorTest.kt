package com.picpay.desafio.android.user.domain.interactor

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import com.nhaarman.mockitokotlin2.any
import com.picpay.desafio.android.DumbData
import com.picpay.desafio.android.feature.user.data.datasource.UserDataSource
import com.picpay.desafio.android.feature.user.data.repository.UserRepository
import com.picpay.desafio.android.feature.user.domain.interactor.UserInteractor
import com.picpay.desafio.android.feature.user.domain.interactor.UserInteractorImpl
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenter
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UserInteractorTest {

    @Mock
    private lateinit var mUserRepository: UserRepository

    @Mock
    private lateinit var mUserPresenter: UserPresenter

    @Mock
    private lateinit var mUserDataSource: UserDataSource

    @Mock
    private lateinit var mContext: Context

    private lateinit var mUserInteractor: UserInteractor

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Mock
    private lateinit var activeNetwork: Network

    @Mock
    private lateinit var networkCapabilities: NetworkCapabilities

    @Mock
    private lateinit var activeNetworkInfo: NetworkInfo


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(mContext.getSystemService(any())).thenReturn(connectivityManager)
        `when`(connectivityManager.activeNetwork).thenReturn(activeNetwork)
        `when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(networkCapabilities)
        `when`(networkCapabilities.hasTransport(any())).thenReturn(true)

        mUserInteractor = UserInteractorImpl(mUserRepository, mUserPresenter, mUserDataSource, mContext)
    }

    @Test
    fun `When called getUser with status true and connection internet`() {

        `when`(connectivityManager.activeNetworkInfo).thenReturn(activeNetworkInfo)
        `when`(activeNetworkInfo.type).thenReturn(TYPE_WIFI)


        mUserInteractor.getUser()

        `when`(mUserDataSource.statusUser()).thenReturn(true)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(Unit)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(mUserPresenter.showUsers(DumbData.getUserList()))
    }

    @Test
    fun `When called getUser with status false and connection internet`() {

        `when`(connectivityManager.activeNetworkInfo).thenReturn(activeNetworkInfo)
        `when`(activeNetworkInfo.type).thenReturn(TYPE_WIFI)


        `when`(mUserDataSource.statusUser()).thenReturn(false)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(Unit)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(mUserPresenter.showUsers(DumbData.getUserList()))
    }

    @Test
    fun `When called getUser with status true and don't connection internet`() {

        mUserInteractor.getUser()

        `when`(mUserDataSource.statusUser()).thenReturn(true)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(Unit)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(mUserPresenter.showUsers(DumbData.getUserList()))
    }

    @Test
    fun `When called getUser with status false and don't connection internet`() {

        `when`(mUserDataSource.statusUser()).thenReturn(false)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(Unit)

        Assertions.assertThat(mUserInteractor.getUser()).isEqualTo(mUserPresenter.showUsers(DumbData.getUserList()))
    }


}