package com.example.freepsplusgamesnotifier.presentation.search_game

import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.domain.model.SearchedGame

data class SearchGameState(
    val data: List<SearchedGame>? = null,
    val isLoading: Boolean = false,
    val error: String? = EMPTY_STRING
)