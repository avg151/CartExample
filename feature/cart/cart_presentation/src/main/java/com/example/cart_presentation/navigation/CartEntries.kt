package com.example.cart_presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.cart_presentation.ui.screen.CartScreen

fun EntryProviderScope<NavKey>.cartEntries() {
    entry<CartFeatureKey> {
        CartScreen()
    }
}
