package com.example.cart_presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CartSummaryBar(
    totalItems: Int,
    totalPrice: Long,
    onOpenCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Items: $totalItems")
                Text("Total: $totalPrice")
            }
            Button(onClick = onOpenCart) {
                Text("Cart")
            }
        }
    }
}

@Preview
@Composable
fun CartSummaryBarPreview() {
    CartSummaryBar(
        totalItems = 10,
        totalPrice = 10,
        onOpenCart = {},
    )
}
