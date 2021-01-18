package com.picpay.desafio.android.feature.user.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.commons.api.ApiClient
import com.picpay.desafio.android.commons.utils.Constants
import com.picpay.desafio.android.feature.user.data.datasource.UserLocalDataSource
import com.picpay.desafio.android.feature.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.feature.user.domain.interactor.UserInteractorImpl
import com.picpay.desafio.android.feature.user.domain.model.User
import com.picpay.desafio.android.feature.user.ui.adapter.UserAdapter
import com.picpay.desafio.android.feature.user.ui.presenter.UserPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : UserView, AppCompatActivity() {

    private val mUserPresenter by lazy {
        UserPresenterImpl(this)
    }

    private val mApiClient by lazy {
        ApiClient()
    }

    private val mUserRepository by lazy {
        UserRepositoryImpl(mApiClient)
    }

    private val mUserDataSource by lazy {
        UserLocalDataSource(getSharedPreferences(Constants.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE))
    }

    private val mUserInteractor by lazy {
        UserInteractorImpl(mUserRepository, mUserPresenter, mUserDataSource, this)
    }

    private val mAdapter by lazy {
        UserAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    override fun onResume() {
        super.onResume()

        startLoading()
        mUserInteractor.getUser()
    }

    private fun setupView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@UserActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mAdapter
        }
    }

    private fun startLoading() {
        user_list_progress_bar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        user_list_progress_bar.visibility = View.GONE
    }

    override fun showUsers(list: List<User>) {
        stopLoading()
        mAdapter.updateItems(list)
    }

    override fun showErrorMessage(message: String) {
        stopLoading()
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(android.R.string.yes))
            .setMessage(message)
            .setPositiveButton(getString(android.R.string.yes)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun showErrorNetwork() {
        stopLoading()
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
    }

}