package com.example.design_system.components.test.round_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundImageExample() {
    Image(
        painter = painterResource(id = android.R.drawable.ic_btn_speak_now),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.Red)
    )
}

@Composable
@Preview(showBackground = true)
fun RoundImageExamplePreview() {
    RoundImageExample()
}