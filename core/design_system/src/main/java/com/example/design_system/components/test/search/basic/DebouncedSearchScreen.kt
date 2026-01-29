package com.example.design_system.components.test.search.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun DebouncedSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<String>>(emptyList()) }

    // Этот LaunchedEffect будет перезапускаться при каждом изменении searchQuery
    LaunchedEffect(searchQuery) {
        // Игнорируем пустые или состоящие из пробелов запросы
        if (searchQuery.isNotBlank()) {
            // Ждем 500 мс
            delay(500)
            // После 500 мс, если searchQuery все еще тот же, выполняем поиск
            // В реальном приложении здесь будет вызов API
            searchResults = listOf("Результат для '$searchQuery' 1", "Результат для '$searchQuery' 2")
        } else {
            searchResults = emptyList()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Поиск") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(searchResults) { result ->
                Text(result, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebouncedSearchScreenPreview() {
    DebouncedSearchScreen()
}
