package com.example.cart_presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cart_presentation.ui.CartScreen
import com.example.cart_presentation.vm.CartViewModel

fun EntryProviderScope<NavKey>.cartEntries(
    navigator: CartNavigator
) {
    entry<CartKey> {
        val vm: CartViewModel = viewModel()
        CartScreen(
//            vm = vm,
            onBack = { navigator.back() }
        )
    }
}
