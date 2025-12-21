package com.example.design_system.components.summary.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.design_system.components.summary.data.SummaryActionBarUiData

class SummaryActionBarPreviewProvider : PreviewParameterProvider<SummaryActionBarUiData> {
    override val values: Sequence<SummaryActionBarUiData> = sequenceOf(
        SummaryActionBarUiData(
            title = "Items: 0",
            subtitle = "Total: 0",
            actionText = "Cart",
            enabled = false
        ),
        SummaryActionBarUiData(
            title = "Items: 3",
            subtitle = "Total: 1250000",
            actionText = "Cart",
            enabled = true
        ),
        SummaryActionBarUiData(
            title = "3 items",
            subtitle = null,
            actionText = "Checkout",
            enabled = true
        ),
    )
}