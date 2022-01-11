package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.ui.theme.chipTextColor

@Composable
fun Chip(modifier: Modifier = Modifier, chipText: String) {
    Text(
        text = chipText,
        color = MaterialTheme.colors.chipTextColor,
        style = MaterialTheme.typography.body2,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}

@Preview
@Composable
fun ChipPreview() {
    Chip(chipText = "Test")
}