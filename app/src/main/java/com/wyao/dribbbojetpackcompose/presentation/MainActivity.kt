package com.wyao.dribbbojetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wyao.dribbbojetpackcompose.presentation.auth.AuthScreen
import com.wyao.dribbbojetpackcompose.presentation.login.LoginScreen
import com.wyao.dribbbojetpackcompose.presentation.splash.SplashScreen
import com.wyao.dribbbojetpackcompose.presentation.ui.MainScreen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.DribbboTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DribbboTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(
                            route = Screen.SplashScreen.route
                        ) {
                            SplashScreen(navController)
                        }
                        composable(
                            route = Screen.AuthScreen.route
                        ) {
                            AuthScreen(navController)
                        }
                        composable(
                            route = Screen.LoginScreen.route + "/{accessToken}",
                            arguments = listOf(
                                navArgument("accessToken") {
                                    type = NavType.StringType
                                    defaultValue = null
                                    nullable = true
                                }
                            )
                        ) {
                            LoginScreen(navController)
                        }
                        composable(
                            route = Screen.MainScreen.route
                        ) {
                            MainScreen(navController)
                        }
                    }
                }
            }
        }
    }
}