package com.example.core.navigation_impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import com.example.core.navigation.LocalAppNavigator

@Composable
fun ProvideAppNavigator(
    backStack: MutableList<NavKey>,
    content: @Composable () -> Unit
) {
    val navigator = remember(backStack) { AppNavigatorImpl(backStack) }
    CompositionLocalProvider(LocalAppNavigator provides navigator) {
        content()
    }
}