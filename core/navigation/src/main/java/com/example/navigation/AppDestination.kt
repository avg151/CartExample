package com.example.navigation

sealed interface AppDestination

data object AuthDestination : AppDestination
data object ProfileDestination : AppDestination
data object SettingsDestination : AppDestination
