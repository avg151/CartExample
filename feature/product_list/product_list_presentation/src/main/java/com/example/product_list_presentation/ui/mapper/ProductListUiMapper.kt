package com.example.product_list_presentation.ui.mapper

import com.example.product_list_domain.model.Money
import com.example.product_list_domain.model.ProductItem
import com.example.product_list_presentation.ui.model.ProductUiModel

internal fun List<ProductItem>.toUiModel(): List<ProductUiModel> =
    this.map { it.toUiModel() }


private fun ProductItem.toUiModel(): ProductUiModel =
    ProductUiModel(
        id = this.id,
        title = this.title,
        priceText = formatPrice(
            price = this.money
        )
    )

private fun formatPrice(price: Money): String {
    return price.toString()
}
