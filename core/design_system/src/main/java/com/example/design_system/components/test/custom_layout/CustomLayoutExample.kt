package com.example.design_system.components.test.custom_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * Пример кастомного лейаута, который размещает элементы (бейджи) в ряд
 * и переносит их на следующую строку, если они не помещаются по ширине.
 */
@Composable
fun BadgeFlowLayout(
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = 8.dp,
    verticalSpacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables: List<Measurable>, constraints: Constraints ->
        // 1. Измеряем всех детей
        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0, minHeight = 0)) }

        val layoutWidth = constraints.maxWidth
        val rows = mutableListOf<List<Placeable>>()
        var currentRow = mutableListOf<Placeable>()
        var currentRowWidth = 0
        var totalHeight = 0

        val hSpacingPx = horizontalSpacing.roundToPx()
        val vSpacingPx = verticalSpacing.roundToPx()

        // Группируем элементы по строкам
        placeables.forEach { placeable ->
            if (currentRowWidth + placeable.width > layoutWidth && currentRow.isNotEmpty()) {
                rows.add(currentRow)
                totalHeight += (currentRow.maxOf { it.height } + vSpacingPx)
                currentRow = mutableListOf()
                currentRowWidth = 0
            }
            currentRow.add(placeable)
            currentRowWidth += placeable.width + hSpacingPx
        }
        rows.add(currentRow)
        totalHeight += currentRow.maxOfOrNull { it.height } ?: 0

        // 2. Определяем размер самого контейнера
        layout(layoutWidth, totalHeight) {
            var y = 0
            rows.forEach { row ->
                var x = 0
                val rowHeight = row.maxOf { it.height }
                row.forEach { placeable ->
                    // 3. Размещаем элементы
                    placeable.placeRelative(x = x, y = y)
                    x += placeable.width + hSpacingPx
                }
                y += rowHeight + vSpacingPx
            }
        }
    }
}

@Composable
fun Badge(
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutExamplePreview() {
    MaterialTheme {
        val labels = listOf(
            "Kotlin", "Compose", "Android", "Custom Layout",
            "Development", "UI/UX", "Mobile", "Jetpack", "Performance", "Badge"
        )

        BadgeFlowLayout(
            modifier = Modifier
                .padding(16.dp)
        ) {
            labels.forEach { label ->
                Badge(text = label)
            }
        }
    }
}
