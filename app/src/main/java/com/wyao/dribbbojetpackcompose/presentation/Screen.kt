package com.wyao.dribbbojetpackcompose.presentation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object AuthScreen: Screen("auth_screen")
}
