package com.wyao.dribbbojetpackcompose.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyao.dribbbojetpackcompose.R
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.white

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Shot () {
    Column(Modifier.background(color = white)) {
        ShotImagePreview()
        ShotInfo()
    }
}

@Composable
fun ShotImagePreview () {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 4.dp, 4.dp, 0.dp)
            .background(white)
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_1),
            contentDescription = "Shot",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ShotInfo () {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .padding(4.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_1),
            contentDescription = "Author avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp)
                .align(CenterVertically),
        )
        Text(
            text = "Van Gao",
            fontSize = 14.sp,
            modifier = Modifier
                .align(CenterVertically)
                .padding(4.dp, 0.dp),
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_favorite_gray_18dp),
                contentDescription = "Favorite",
                modifier = Modifier
                    .align(CenterVertically)
                    .size(12.dp)
            )
            Text(
                text = "690",
                fontSize = 12.sp,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(2.dp, 2.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                painter = painterResource(R.drawable.ic_visibility_gray_18dp),
                contentDescription = "Visibility",
                modifier = Modifier
                    .align(CenterVertically)
                    .size(12.dp)
            )
            Text(
                text = "7.6k",
                fontSize = 12.sp,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(2.dp, 4.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }
}