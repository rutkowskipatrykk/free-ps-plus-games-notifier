package com.example.freepsplusgamesnotifier.presentation.search_game

import com.example.freepsplusgamesnotifier.domain.model.SearchedGame

data class SearchGameState(
    val data: List<SearchedGame>? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)