package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.GameListItem

data class GameListItemDto(
    val id: Int,
    val name: String,
    val totalRating: String,
    val cover: String,
    val platforms: String
)

fun GameListItemDto.toGameListItem() =
    GameListItem(
        id = id,
        name = name,
        totalRating = "5",
        cover = cover,
        platforms = platforms
    )