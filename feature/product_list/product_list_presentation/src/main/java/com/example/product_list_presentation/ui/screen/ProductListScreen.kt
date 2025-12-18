package com.example.product_list_presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.navigation.LocalAppNavigator
import com.example.product_list_presentation.ui.screen.states.ProductListDataScreen
import com.example.product_list_presentation.ui.screen.states.ProductListErrorScreen
import com.example.product_list_presentation.ui.screen.states.ProductListLoadingScreen
import com.example.product_list_presentation.vm.ProductListViewModel

@Composable
fun ProductListScreen(
    vm: ProductListViewModel = hiltViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding)
        ) {
            when {
                state.isLoading -> ProductListLoadingScreen()
                state.error != null -> ProductListErrorScreen(state.error)
                else -> ProductListDataScreen(state)
            }
        }
    }
}