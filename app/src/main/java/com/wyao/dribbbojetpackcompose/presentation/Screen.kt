package com.wyao.dribbbojetpackcompose.presentation

import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object MainScreen: Screen("main_screen")
    object LoginScreen: Screen("login_screen")
    object AuthScreen: Screen("auth_screen")
    object ShotListScreen: Screen("shortlist_screen")

    fun withArgs(vararg args: String?):String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
