package com.example.product_list_presentation.ui

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
import com.example.product_list_presentation.vm.ProductListViewModel

@Composable
fun ProductListScreen(
    vm: ProductListViewModel,
    onOpenProduct: (Int) -> Unit,
    onOpenCart: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Product List")
            Spacer(Modifier.height(16.dp))

            Button(onClick = { onOpenProduct(1) }) {
                Text("Открыть продукт")
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = onOpenCart) {
                Text("В корзину")
            }
        }
    }
}