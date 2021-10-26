package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val LOW_RATE_LEVEL = 50
private const val MIDDLE_RATE_LEVEL = 80

@Composable
fun RateArc(rate: Int, backgroundColor: Color, modifier: Modifier = Modifier) {
    val color = when {
        rate < LOW_RATE_LEVEL -> Color.Red
        rate < MIDDLE_RATE_LEVEL -> Color.Yellow
        else -> Color.Green
    }
    Box(modifier = modifier) {
        Box {
            Canvas(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .padding(4.dp)
            ) {
                drawRoundRect(color, cornerRadius = CornerRadius(32f, 32f), style = Stroke(7f))
            }
            Text(
                text = "$rate",
                color = color,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = "%",
            color = color,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(backgroundColor)
        )
    }
}

@Composable
@Preview
fun RateArcPreview() {
    RateArc(80, Color.White)
}