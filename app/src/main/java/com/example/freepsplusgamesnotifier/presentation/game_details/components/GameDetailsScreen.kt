package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.freepsplusgamesnotifier.presentation.game_details.GameState
import com.example.freepsplusgamesnotifier.presentation.game_details.view_model.GameDetailsViewModel

@Composable
fun GameDetailsScreen(
    gameId: Int?,
    gameDetailsViewModel: GameDetailsViewModel = hiltViewModel()
) {
    gameDetailsViewModel.getDetails(gameId!!)
    GameDetailsContent()
}

@Composable
fun GameDetailsContent(gameDetailsViewModel: GameDetailsViewModel = hiltViewModel()) {
    val gameState = gameDetailsViewModel.gameState.value
    when {
        gameState.isDownloading -> {
            CircularProgressIndicator()
        }
        gameState.error?.isNotEmpty() == true -> {
            Box {
                Text(text = "Error")
            }
        }
        gameState.data != null -> {
            Box {
                Text(text = gameState.data.name)
            }
        }
    }
}