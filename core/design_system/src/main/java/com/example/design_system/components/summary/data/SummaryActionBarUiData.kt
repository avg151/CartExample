package com.example.design_system.components.summary.data

import androidx.compose.ui.graphics.vector.ImageVector

data class SummaryActionBarUiData(
    val title: String,
    val subtitle: String? = null,
    val actionText: String,
    val enabled: Boolean = true,
    val leadingIcon: ImageVector? = null,
)