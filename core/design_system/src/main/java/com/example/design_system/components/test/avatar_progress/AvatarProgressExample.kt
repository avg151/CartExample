package com.example.design_system.components.test.avatar_progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AvatarWithProgress(
    modifier: Modifier = Modifier,
    avatar: Painter,
    progress: Float,
    avatarSize: Dp = 100.dp,
    progressArcWidth: Dp = 8.dp,
    progressArcColor: Color = Color.Blue,
    backgroundArcColor: Color = Color.LightGray,
) {
    Box(
        modifier = modifier.size(avatarSize),
        contentAlignment = Alignment.Center
    ) {
        val sweepAngle = 360 * progress

        Canvas(modifier = Modifier.size(avatarSize)) {
            val strokeWidth = progressArcWidth.toPx()

            // Background arc
            drawCircle(
                color = backgroundArcColor,
                style = Stroke(width = strokeWidth)
            )

            // Progress arc
            drawArc(
                color = progressArcColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        Image(
            painter = avatar,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(avatarSize - (progressArcWidth * 2))
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarProgressExample() {
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AvatarWithProgress(
            avatar = ColorPainter(Color.Red),
            progress = animatedProgress,
        )

        Spacer(Modifier.height(20.dp))

        Button(onClick = { progress = if (progress < 1f) progress + 0.1f else 0.1f }) {
            Text("Animate")
        }
    }
}
