package com.example.product_list_presentation.vm

data class ProductListUiState(
    val isLoading: Boolean = true,
    val items: List<String> = emptyList(),
    val error: String? = null
)