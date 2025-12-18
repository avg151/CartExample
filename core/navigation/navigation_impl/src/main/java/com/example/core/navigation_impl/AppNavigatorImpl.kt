package com.example.core.navigation_impl

import androidx.navigation3.runtime.NavKey
import com.example.cart_presentation.navigation.CartFeatureKey
import com.example.core.navigation.AppNavigator
import com.example.product_detail_presentation.navigation.ProductDetailFeatureKey

class AppNavigatorImpl(
    private val backStack: MutableList<NavKey>
) : AppNavigator {

    override fun openProduct(id: String) {
        backStack.add(ProductDetailFeatureKey(id))
    }

    override fun openCart() {
        backStack.add(CartFeatureKey)
    }

    override fun back() {
        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
    }
}