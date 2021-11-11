package com.example.freepsplusgamesnotifier.presentation.game_details

import com.example.freepsplusgamesnotifier.domain.model.GameDetailsData

data class GameState(
    val data: GameDetailsData? = null,
    val isDownloading: Boolean = false,
    val error: String? = null
)