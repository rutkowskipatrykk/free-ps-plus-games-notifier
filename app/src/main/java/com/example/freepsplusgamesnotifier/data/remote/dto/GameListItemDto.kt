package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.GameListItem

data class GameListItemDto(
    val id: Int,
    val name: String,
    val platforms: String,
    val rating: String
)

fun GameListItemDto.toGameListItem() =
    GameListItem(
        id = id,
        name = name,
        platforms = platforms,
        rating = rating
    )