package com.example.design_system.components.summary


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.design_system.components.summary.data.SummaryActionBarUiData
import com.example.design_system.components.summary.preview.SummaryActionBarPreviewProvider

@Composable
fun SummaryActionBar(
    data: SummaryActionBarUiData,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Row(
           modifier =  Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)) {
                Text(text = data.title, style = MaterialTheme.typography.titleMedium)
                data.subtitle?.let { subtitle ->
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Button(
                onClick = onAction,
                enabled = data.enabled,
            ) {
                Text(data.actionText)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SummaryActionBarPreview(
    @PreviewParameter(SummaryActionBarPreviewProvider::class) data: SummaryActionBarUiData
) {
    SummaryActionBar(
        data = data,
        onAction = {}
    )
}