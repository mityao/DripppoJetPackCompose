package com.wyao.dribbbojetpackcompose.presentation.auth

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.presentation.Screen
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.ColorPrimary


@Composable
fun AuthScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val state = authViewModel.state.value
    AuthContent(navController, authViewModel, state)
    Log.d("WEI", "AuthScreen authViewModel.state.value")
}

@Composable
fun AuthContent(
    navController: NavController,
    authViewModel: AuthViewModel,
    state: AuthState
) {
    var onPageStarted by remember { mutableStateOf(false) }
    var onPageFinished by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.1f) }
    var isRedirected by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        if (url != null && url.startsWith(authViewModel.getRedirectUri())) {
                            val uri = Uri.parse(url)
                            val accessCode = uri.getQueryParameter(authViewModel.getKeyCode())
                            // use accessCode, get accessToken
                            if (accessCode != null && !isRedirected) {
                                authViewModel.fetchAccessToken(accessCode)
                                Log.d("WEI", "AuthScreen Get code: $accessCode" )
                            }
                            isRedirected = true
                        }
                        return super.shouldOverrideUrlLoading(view, url)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        if (!isRedirected) {
                            onPageStarted = true
                            onPageFinished = false
                        }
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        onPageStarted = false
                        onPageFinished = true
                    }
                }
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        progress = newProgress.toFloat()
                    }
                }
                Log.d("WEI", "AuthScreen loadUrl")
                loadUrl(authViewModel.getAuthorizeUrl())
            }
        })

        AuthLoadProgressBar(onPageStarted, progress)

        if (state.isLoading) {
            CircularProgressIndicator(
                color = ColorPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (state.accessTokenDto != null && authViewModel.getUpdateAccessTokenString() == null) {
            Log.d("WEI", "AuthScreen Got token ${state.accessTokenDto.access_token}")
            authViewModel.updateAccessTokenString()
            navController.navigate(Screen.LoginScreen.withArgs("true"))
        }
    }
}

@Composable
fun AuthLoadProgressBar(isVisible: Boolean,
                        progress: Float) {
    if (isVisible) {
        val animatedProgress = animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        ).value

        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
        )
    }
}
