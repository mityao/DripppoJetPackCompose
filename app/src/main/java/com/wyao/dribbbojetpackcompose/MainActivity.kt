package com.wyao.dribbbojetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.wyao.dribbbojetpackcompose.ui.MainScaffold
import com.wyao.dribbbojetpackcompose.ui.Shot
import com.wyao.dribbbojetpackcompose.ui.SignInAndSinOnButtons

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainScaffold() }
    }
}