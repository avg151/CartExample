package com.example.navigation

sealed interface NavigationEvent {
    data class Navigate(val destination: AppDestination) : NavigationEvent
    data object Back : NavigationEvent
}