package com.example.core.navigation

import com.example.core.navigation.feature_navigators.CartNavigator
import com.example.core.navigation.feature_navigators.ProductDetailNavigator
import com.example.core.navigation.feature_navigators.ProductListNavigator

interface AppNavigator : ProductListNavigator,
    ProductDetailNavigator,
    CartNavigator