package com.wyao.dribbbojetpackcompose.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.presentation.Screen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.white

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 32.dp)
    ) {
        AuthenticationButton(loginViewModel, navController,"Explore the world!")
    }
}

@Composable
fun AuthenticationButton (loginViewModel: LoginViewModel, 
                          navController: NavController,
                          buttonText: String, 
                          modifier: Modifier = Modifier) {
    Button (
        onClick = {
                  if (!loginViewModel.isLoggedIn()) {
                      navController.navigate(Screen.AuthScreen.route)
                  }
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




