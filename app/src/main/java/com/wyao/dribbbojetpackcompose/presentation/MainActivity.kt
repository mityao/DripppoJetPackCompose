package com.wyao.dribbbojetpackcompose.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wyao.dribbbojetpackcompose.presentation.auth.AuthScreen
import com.wyao.dribbbojetpackcompose.presentation.login.LoginScreen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.DribbboTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DribbboTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(
                            route = Screen.LoginScreen.route + "/{accessToken}"
                        ) {
                            LoginScreen(navController)
                        }
                        composable(
                            route = Screen.AuthScreen.route
                        ) {
//                            AuthScreen()
                        }
                    }
                }
            }
        }
    }
}