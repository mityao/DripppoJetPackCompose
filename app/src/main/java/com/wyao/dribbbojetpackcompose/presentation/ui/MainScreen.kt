package com.wyao.dribbbojetpackcompose.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wyao.dribbbojetpackcompose.R
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.purple700
import com.wyao.dribbbojetpackcompose.presentation.ui.theme.white

val drawerItemPadding = Modifier.padding(24.dp, 12.dp, 12.dp, 12.dp)
val myTabs = listOf(
    TabItem.All,
    TabItem.Animation,
    TabItem.Branding,
    TabItem.Illustration,
    TabItem.Mobile,
    TabItem.Print,
    TabItem.ProductDesign,
    TabItem.Typography,
    TabItem.WebDesign,
)

@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = { AppToolBar() },
        drawerContent = { NavigationDrawer() },
        drawerBackgroundColor = white
    ) {
        Tabs(myTabs)
    }

}
@Composable
fun AppToolBar() {
    TopAppBar (
        title = {
            Text(
                text = "Dribbbo",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily.Cursive,
                    fontSize = 24.sp
                ),
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        })
}

@Composable
fun NavigationDrawer() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(24.dp, 48.dp, 16.dp, 48.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier.size(86.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar_1),
                        contentDescription = "User Avatar",
                    )
                }
                Icon(
                    Icons.Filled.Camera,
                    contentDescription = "Update Avatar",
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
            ) {
                Text(
                    text = "UserName",
                    color = white,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
        Column() {
            Text(
                text = "Profile",
                style = TextStyle(white, 16.sp, fontFamily = FontFamily.Monospace),
                modifier = drawerItemPadding.align(Alignment.Start)
            )
        }
    }
}


@Composable
fun Tabs(tabs: List<TabItem>) {
    var tabIndex by remember { mutableStateOf(0) }
    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(selected = index == tabIndex, onClick = {
                tabIndex = index
            }, text = {
                Text(text = tabItem.title)
            })
        }
    }
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}