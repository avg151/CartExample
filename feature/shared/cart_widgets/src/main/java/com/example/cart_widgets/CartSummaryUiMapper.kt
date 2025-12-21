package com.example.cart_widgets

import com.example.cart_domain.model.CartSummary
import javax.inject.Inject

class CartSummaryUiMapper @Inject constructor() {
    fun map(summary: CartSummary): CartSummaryUi =
        CartSummaryUi(
            totalItems = summary.totalItems,
            totalPriceText = summary.totalPrice.toString(),
            enabled = summary.totalItems > 0
        )
}