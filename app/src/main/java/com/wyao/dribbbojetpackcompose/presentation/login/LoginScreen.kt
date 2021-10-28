package com.wyao.dribbbojetpackcompose.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.presentation.Screen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.ColorPrimary
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.white

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val state = loginViewModel.state.value
    var authButtonVisible by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column (
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 32.dp)
        ) {
            AuthenticationButton(navController,"Explore the world!", authButtonVisible)
        }
    }

    if (state.isLoading) {
        authButtonVisible = false
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                color = ColorPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    if (state.user != null) {
        authButtonVisible = false
        ReadyToGo(navController)
        Log.d("WEI", state.user.name)
    }
}

@Composable
fun AuthenticationButton (navController: NavController,
                          buttonText: String, 
                          visible: Boolean
) {
    if (visible) {
        Button (
            onClick = {
                navController.navigate(Screen.AuthScreen.route)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp, 16.dp, 4.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text (
                text = buttonText,
                fontSize = 16.sp,
                color = white,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ReadyToGo(navController: NavController) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = "Your account is setup") },
        text = { Text(text = "Let's GO") },
        confirmButton = {
            Button(
                onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }) {
                Text("Ok")
            }
        }
    )
}




