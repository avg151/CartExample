package com.example.design_system.components.test.timer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun TimerScreen(onTimerFinished: () -> Unit) {
    var timeLeft by remember { mutableIntStateOf(10) }

    // Создаем "стабильную" ссылку на постоянно меняющийся callback
    val currentOnTimerFinished by rememberUpdatedState(onTimerFinished)

    LaunchedEffect(Unit) { // Запускается один раз при входе в композицию
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        // Вызываем именно актуальную версию через делегат
        currentOnTimerFinished()
    }

    Text("Осталось: $timeLeft")
}

@Preview
@Composable
fun TimerScreenPreview() {
    TimerScreen(
        {}
    )
}

