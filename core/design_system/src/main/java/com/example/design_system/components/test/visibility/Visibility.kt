package com.example.design_system.components.test.visibility

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Visibility() {
    var isVisible by remember { mutableStateOf(true) }

    Column {
        if (isVisible) {
            Text(text = "This text is visible.")
        }

        Button(onClick = { isVisible = !isVisible }) {
            Text(text = if (isVisible) "Hide" else "Show")
        }
    }
}

@Preview
@Composable
private fun VisibilityPreview() {
    Visibility()
}