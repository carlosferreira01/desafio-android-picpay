package com.picpay.desafio.android.user.ui.presenter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.DumbData
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenter
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenterImpl
import com.picpay.desafio.android.feature.user.ui.view.UserActivity
import com.picpay.desafio.android.feature.user.ui.view.UserView
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserPresenterTest {

    @Mock
    private lateinit var userPresenter: UserPresenter

    @Mock
    private var userView: UserView = mock()

    @Mock
    private var userActivity: UserActivity = mock()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userPresenter = UserPresenterImpl(userActivity)
    }

    @Test
    fun `When called showUsers`() {
        userPresenter.showUsers(DumbData.getUserList())
        Assertions.assertThat(userPresenter.showUsers(DumbData.getUserList())).isEqualTo(userView.showUsers(DumbData.getUserList()))
    }


    @Test
    fun `When called showErrorMessage`() {
        userPresenter.showErrorMessage("Error")
        Assertions.assertThat(userPresenter.showErrorMessage("Error")).isEqualTo(userView.showErrorMessage("Error"))
    }

    @Test
    fun `When called showErrorNetwork`() {
        userPresenter.showErrorNetwork()
        Assertions.assertThat(userPresenter.showErrorNetwork()).isEqualTo(userView.showErrorNetwork())
    }

}