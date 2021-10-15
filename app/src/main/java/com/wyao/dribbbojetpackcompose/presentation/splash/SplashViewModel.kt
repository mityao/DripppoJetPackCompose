package com.wyao.dribbbojetpackcompose.presentation.splash

import androidx.lifecycle.ViewModel
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dribbboRepository: DribbboRepository): ViewModel() {

    fun isLoggedIn(): Boolean {
        return dribbboRepository.isLoggedIn()
    }
}