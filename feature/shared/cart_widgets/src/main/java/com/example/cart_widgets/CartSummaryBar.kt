package com.example.cart_widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.design_system.components.summary.SummaryActionBar
import com.example.design_system.components.summary.data.SummaryActionBarUiData


@Composable
fun CartSummaryBar(
    totalItems: Int,
    totalPriceText: String,
    enabled: Boolean,
    onOpenCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SummaryActionBar(
        data = SummaryActionBarUiData(
            title = "Items: $totalItems",
            subtitle = "Total: $totalPriceText",
            actionText = "Cart",
            enabled = enabled
        ),
        onAction = onOpenCart,
        modifier = modifier
    )
}