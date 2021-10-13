package com.wyao.dribbbojetpackcompose.presentation.auth

import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.presentation.Screen


@Composable
fun AuthScreen(navController: NavController,
               authViewModel: AuthViewModel) {

    var onPageStarted by remember { mutableStateOf(false) }
    var onPageFinished by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.1f) }

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
                            if (accessCode != null) {
                                lateinit var accessToken: AccessToken
                                suspend {
                                    accessToken = authViewModel.fetchAccessToken(accessCode)
                                }
                                navController.navigate(Screen.LoginScreen.route + "/${accessToken.accessToken}")
                            }
                        }
                        return super.shouldOverrideUrlLoading(view, url)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        onPageStarted = true
                        onPageFinished = false
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
                loadUrl(authViewModel.getAuthorizeUrl())
            }
        })

        AuthLoadProgressBar(onPageStarted, progress)
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
