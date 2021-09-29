package com.wyao.dribbbojetpackcompose.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.color.MaterialColors
import com.wyao.dribbbojetpackcompose.ui.theme.black
import com.wyao.dribbbojetpackcompose.ui.theme.white

@Composable
fun SignIn() {

}

@Composable
fun SignUp() {

}

@Preview
@Composable
fun SignInAndSinOnButtons() {
    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(0.dp, 32.dp)
    ) {
        AuthenticationButton("SIGN ON")
        AuthenticationButton("SIGN IN")
        AuthenticationButton("SKIP")
    }
}

@Composable
fun AuthenticationButton (buttonText: String, modifier: Modifier = Modifier) {
    Button (
        onClick = {Log.d("Login", "Button clicked")},
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




