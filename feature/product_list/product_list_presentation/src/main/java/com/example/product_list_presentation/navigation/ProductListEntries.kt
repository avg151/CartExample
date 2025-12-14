package com.example.product_list_presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.product_list_presentation.ui.ProductListScreen
import com.example.product_list_presentation.vm.ProductListViewModel

fun EntryProviderScope<NavKey>.productListEntries(
    navigator: ProductListNavigator
) {
    entry<ProductListKey> {
        val vm: ProductListViewModel = viewModel()
        ProductListScreen(
            vm = vm,
            onOpenProduct = { id -> navigator.openProduct(id) },
            onOpenCart = { navigator.openCart() }
        )
    }
}
