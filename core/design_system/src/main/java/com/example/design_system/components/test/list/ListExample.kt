package com.example.design_system.components.test.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class UserData(
    val id: Int,
    val name: String
)

@Composable
fun ListExample(
    users: List<UserData>
) {
    var selectedId by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(
            items = users,
            key = { user -> user.id }
        ) { userData ->
            UserItem(
                userData = userData,
                isSelected = userData.id == selectedId,
                onClick = { selectedId = userData.id }
            )
        }
    }

}

@Composable
fun UserItem(
    userData: UserData,
    onClick: () -> Unit = {},
    isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(userItemBackgroundColor(isSelected))
            .padding(16.dp)
    ) {
        Text(
            text = userData.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun userItemBackgroundColor(isSelected: Boolean): Color {
    return if (isSelected) {
        MaterialTheme.colorScheme.error

    } else {
        MaterialTheme.colorScheme.background
    }
}

@Composable
@Preview
fun ListExamplePreview() {
    ListExample(
        users = listOf(
            UserData(1, "Один"),
            UserData(2, "Два"),
            UserData(3, "Три")
        )
    )
}

