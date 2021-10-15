package com.wyao.dribbbojetpackcompose.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wyao.dribbbojetpackcompose.presentation.auth.AuthScreen
import com.wyao.dribbbojetpackcompose.presentation.login.LoginScreen
import com.wyao.dribbbojetpackcompose.presentation.splash.SplashScreen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.DribbboTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
                            route = Screen.LoginScreen.route
                        ) {
                            LoginScreen(navController)
                        }
                        composable(
                            route = Screen.AuthScreen.route
                        ) {
                            AuthScreen(navController)
                        }
                    }
                }
            }
        }
    }
}