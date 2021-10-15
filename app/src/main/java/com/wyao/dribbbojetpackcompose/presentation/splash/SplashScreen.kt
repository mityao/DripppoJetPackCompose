package com.wyao.dribbbojetpackcompose.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.R
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.presentation.Screen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.black
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        val route =  when (splashViewModel.isLoggedIn()) {
            true -> Screen.MainScreen.route
            false -> Screen.LoginScreen.route
        }
        navController.popBackStack()
        navController.navigate(route)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().scale(scale.value)
    ) {
        DribbboLogo()
        DribbboLabel()
    }
}

@Preview
@Composable
fun Test() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        DribbboLogo()
        DribbboLabel()
    }
}

@Composable
fun DribbboLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_dribbble_logo),
        contentDescription = "DribbboLogo",
        modifier = Modifier.width(240.dp).height(240.dp).padding(4.dp)
    )
}

@Composable
fun DribbboLabel() {
    Text(
        text = Constants.DRIBBBO_APP_NAME,
        fontFamily = FontFamily.Cursive,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        color = black,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

