package com.wyao.dribbbojetpackcompose.ui

import androidx.compose.runtime.Composable

typealias ComparableFun = @Composable () -> Unit

sealed class TabItem (var title: String, var screen: ComparableFun) {
    object All : TabItem("All", { TabItemScreen() })
    object Animation : TabItem("Animation", { TabItemScreen() })
    object Branding : TabItem("Branding", { TabItemScreen() })
    object Illustration : TabItem("Illustration", { TabItemScreen() })
    object Mobile : TabItem("Mobile", { TabItemScreen() })
    object Print : TabItem("Print", { TabItemScreen() })
    object ProductDesign : TabItem("Product Design", { TabItemScreen() })
    object Typography : TabItem("Typography", { TabItemScreen() })
    object WebDesign : TabItem("Web Design", { TabItemScreen() })
}
