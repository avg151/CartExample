package com.example.product_detail_presentation.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.navigation.LocalAppNavigator

@Composable
fun ProductDetailScreen(
    title: String,
) {
    val navigator = LocalAppNavigator.current

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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