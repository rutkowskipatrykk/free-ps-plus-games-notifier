package com.example.freepsplusgamesnotifier.presentation.game_list

import com.example.freepsplusgamesnotifier.domain.model.GameListItem

data class GameListState(
    val data: List<GameListItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)