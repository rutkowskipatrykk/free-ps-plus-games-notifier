package com.example.freepsplusgamesnotifier.presentation.game_details

import com.example.freepsplusgamesnotifier.domain.model.Game

data class GameState(
    val data: Game? = null,
    val isDownloading: Boolean = false,
    val error: String? = null
)