package com.example.core.navigation

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppNavigator: ProvidableCompositionLocal<AppNavigator> = staticCompositionLocalOf {
    error("AppNavigator is not provided")
}