package com.example.product_detail_presentation.vm


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductDetailViewModel : ViewModel() {
    private val _title = MutableStateFlow("Loading...")
    val title: StateFlow<String> = _title

    fun bind(productId: String) {
        _title.value = "Product: $productId"
    }
}