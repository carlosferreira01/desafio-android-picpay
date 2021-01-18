package com.picpay.desafio.android.user.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.picpay.desafio.android.DumbData
import com.picpay.desafio.android.feature.user.data.datasource.UserDataSource
import com.picpay.desafio.android.feature.user.data.datasource.UserLocalDataSource
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class UserLocalDataSource {

    private lateinit var mDataSource: UserDataSource

    lateinit var mPreferences: SharedPreferences

    @Mock
    private lateinit var mContext: Context

    @Mock
    private lateinit var sharedPrefs: SharedPreferences

    @Mock
    private lateinit var sharedPrefsEditor: SharedPreferences.Editor


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(mContext.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)
        `when`(sharedPrefs.edit()).thenReturn(sharedPrefsEditor)
        `when`(sharedPrefsEditor.putString(anyString(), anyString())).thenReturn(sharedPrefsEditor)
        `when`(sharedPrefsEditor.commit()).thenReturn(true)



        mPreferences = mContext.getSharedPreferences("User", 0)

        mDataSource = UserLocalDataSource(mPreferences)
    }

    @Test
    fun saveUserTest() {

        `when`(mDataSource.saveUsers(DumbData.getUserList()))
            .thenReturn(true)

        verify(sharedPrefsEditor, times(1)).putString(anyString(), anyString())

        Assertions.assertThat(mDataSource.saveUsers(DumbData.getUserList()))
            .isTrue()

    }

    @Test
    fun saveUserTest_error() {

        `when`(mDataSource.saveUsers(DumbData.getUserList()))
            .thenReturn(false)

        verify(sharedPrefsEditor, times(1)).putString(anyString(), anyString())

        Assertions.assertThat(mDataSource.saveUsers(DumbData.getUserList()))
            .isFalse()

    }

//    @Test
//    fun getUsersLocal(){
//
//        `when`(mDataSource.getUsersLocal()).thenReturn(DumbData.getUserList())
//
//        Assertions.assertThat(mDataSource.getUsersLocal()).isEqualTo(DumbData.getUserList())
//    }
//
//    @Test
//    fun statusUser(){
//        `when`(mDataSource.statusUser()).thenReturn(true)
//
//        Assertions.assertThat(mDataSource.statusUser()).isTrue()
//    }
//
//    @Test
//    fun deleteUsers(){
//        `when`(mDataSource.deleteUsers()).thenReturn(true)
//
//        verify(sharedPrefsEditor, times(1)).remove(anyString())
//
//        Assertions.assertThat(mDataSource.deleteUsers()).isTrue()
//    }
}