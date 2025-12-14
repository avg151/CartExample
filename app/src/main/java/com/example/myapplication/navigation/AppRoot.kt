package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.cart_presentation.navigation.CartKey
import com.example.cart_presentation.navigation.CartNavigator
import com.example.cart_presentation.navigation.cartEntries
import com.example.product_detail_presentation.navigation.ProductDetailKey
import com.example.product_detail_presentation.navigation.ProductDetailNavigator
import com.example.product_detail_presentation.navigation.productDetailEntries
import com.example.product_list_presentation.navigation.ProductListKey
import com.example.product_list_presentation.navigation.ProductListNavigator
import com.example.product_list_presentation.navigation.productListEntries

@Composable
fun AppRoot() {
    val backStack = rememberNavBackStack(ProductListKey)
    val navigator = remember(backStack) { AppNavigator(backStack) }

    NavDisplay(
        backStack = backStack,
        onBack = { navigator.back() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            productListEntries(navigator)
            productDetailEntries(navigator)
            cartEntries(navigator)
        }
    )
}

@Stable
class AppNavigator(private val backStack: MutableList<NavKey>) :
    ProductListNavigator,
    ProductDetailNavigator,
    CartNavigator {

    override fun openProduct(id: Int) {
        backStack.add(ProductDetailKey(id))
    }

    override fun openCart() {
        backStack.add(CartKey)
    }

    override fun back() {
        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
    }
}
