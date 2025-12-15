package com.example.product_list_presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.product_list_presentation.ui.ProductListScreen

fun EntryProviderScope<NavKey>.productListEntries() {
    entry<ProductListKey> {
        ProductListScreen()
    }
}
