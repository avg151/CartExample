package com.example.product_detail_presentation.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cart_widgets.CartSummaryBarHost
import com.example.core.navigation.LocalAppNavigator

@Composable
fun ProductDetailScreen(
    title: String,
) {
    val navigator = LocalAppNavigator.current

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            CartSummaryBarHost(
                onOpenCart = { navigator.openCart() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                Text(title)
                Spacer(Modifier.height(16.dp))
                Button(onClick = { navigator.openCart() }) {
                    Text("В корзину")
                }
                Button(onClick = { navigator.back() }) {
                    Text("Назад")
                }
            }
        }
    }
}