@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cart_presentation.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cart_presentation.ui.components.CartSummaryBar
import com.example.cart_presentation.vm.CartViewModel
import com.example.core.navigation.LocalAppNavigator

@Composable
fun CartScreen(
    vm: CartViewModel = hiltViewModel(),
) {
    val navigator = LocalAppNavigator.current
    val state by vm.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = { navigator.back() }) { Text("<") }
                },
                actions = {
                    if (state.isRefreshing) {
                        CircularProgressIndicator(Modifier.size(20.dp))
                    } else {
                        TextButton(onClick = vm::onRefresh) { Text("Refresh") }
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier
            .fillMaxSize()
            .padding(padding)) {

            if (state.error != null) {
                Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.items, key = { it.productId }) { item ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.weight(1f)) {
                            Text(item.title)
                            Text("Price: ${item.unitPrice}  |  Line: ${item.lineTotal}")
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(onClick = {
                                vm.onMinus(
                                    item.productId,
                                    item.quantity
                                )
                            }) {
                                Text("-")
                            }
                            Text("${item.quantity}", modifier = Modifier.padding(top = 10.dp))
                            OutlinedButton(onClick = { vm.onPlus(item.productId, item.quantity) }) {
                                Text("+")
                            }
                        }
                    }
                }
            }

            CartSummaryBar(
                totalItems = state.totalItems,
                totalPrice = state.totalPrice,
                onOpenCart = { /* уже на корзине */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}