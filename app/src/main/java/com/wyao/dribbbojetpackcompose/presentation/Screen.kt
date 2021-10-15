package com.wyao.dribbbojetpackcompose.presentation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object MainScreen: Screen("main_screen")
    object LoginScreen: Screen("login_screen")
    object AuthScreen: Screen("auth_screen")
}
