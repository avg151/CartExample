package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.cart_presentation.navigation.cartEntries
import com.example.core.navigation_impl.ProvideAppNavigator
import com.example.product_detail_presentation.navigation.productDetailEntries
import com.example.product_list_presentation.navigation.ProductListKey
import com.example.product_list_presentation.navigation.productListEntries

@Composable
fun AppRootNavDisplay() {
    val backStack = rememberNavBackStack(ProductListKey)

    ProvideAppNavigator(backStack) {
        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                productListEntries()
                productDetailEntries()
                cartEntries()
            }
        )
    }
}