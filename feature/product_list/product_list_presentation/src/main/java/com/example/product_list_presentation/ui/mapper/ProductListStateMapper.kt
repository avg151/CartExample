package com.example.product_list_presentation.ui.mapper

import com.example.product_list_domain.model.ProductItem
import com.example.product_list_presentation.ui.ui_state.ProductListUiState

internal fun errorState(e: Throwable): ProductListUiState =
    ProductListUiState(isLoading = false, error = e.message ?: "Unknown error")

internal fun loadingState(): ProductListUiState = ProductListUiState(isLoading = true)

internal fun dataState(list: List<ProductItem>): ProductListUiState =
    ProductListUiState(isLoading = false, items = list.toUiModel())