package com.example.product_detail_presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailFeatureKey(val id: String) : NavKey