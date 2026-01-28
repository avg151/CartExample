package com.example.design_system.components.test.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Counter() {
    var counter: Int by remember {
        mutableIntStateOf(0)
    }

    Column {
        Text(text = "Counter: $counter")
        Button(
            onClick = { counter++ }
        ) {
            Text(text = "Increment")
        }
    }

}

@Preview
@Composable
private fun CounterPreview() {
    Counter()
}