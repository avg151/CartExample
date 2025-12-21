package com.example.cart_widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CartSummaryBarHost(
    onOpenCart: () -> Unit,
) {
    val vm: CartSummaryBarViewModel = hiltViewModel()
    val state by vm.state.collectAsStateWithLifecycle()

    CartSummaryBar(
        totalItems = state.totalItems,
        totalPriceText = state.totalPriceText,
        enabled = state.enabled,
        onOpenCart = onOpenCart
    )
}