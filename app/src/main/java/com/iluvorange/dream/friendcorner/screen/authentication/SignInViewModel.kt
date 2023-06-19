package com.iluvorange.dream.friendcorner.screen.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel: ViewModel() {

    private val _signUpNavigateClick = MutableLiveData<Boolean>()
    val getSignUpNavigateClick: LiveData<Boolean>
        get() = _signUpNavigateClick


    fun navigateToSignUp(){
        _signUpNavigateClick.value = true
    }
    fun resetNavigateToSignUp(){
        _signUpNavigateClick.value = false
    }
}