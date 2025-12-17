package com.example.product_list_presentation.ui.ui_state

import androidx.compose.runtime.Immutable
import com.example.product_list_presentation.ui.model.ProductUiModel

@Immutable
data class ProductListUiState(
    val isLoading: Boolean = true,
    val items: List<ProductUiModel> = emptyList(),
    val error: String? = null
)