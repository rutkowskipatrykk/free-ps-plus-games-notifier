package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun GameDetailsScreen(gameId: Int?) {
    Box {
        Text(text = "$gameId")
    }
}