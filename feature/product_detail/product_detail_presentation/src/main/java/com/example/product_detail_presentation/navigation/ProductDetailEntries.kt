package com.example.product_detail_presentation.navigation


import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.product_detail_presentation.ui.ProductDetailScreen
import com.example.product_detail_presentation.vm.ProductDetailViewModel

fun EntryProviderScope<NavKey>.productDetailEntries() {
    entry<ProductDetailFeatureKey> { key ->
        val vm: ProductDetailViewModel = viewModel()
        vm.bind(key.id)

        val title = vm.title.collectAsState().value

        ProductDetailScreen(
            title = title,
        )
    }
}
