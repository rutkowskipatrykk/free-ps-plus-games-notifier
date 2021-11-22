package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.GameListItem

data class GameListItemDto(
    val id: Int,
    val name: String,
    val rating: Double,
    val cover: String?,
    val platforms: String
)

fun GameListItemDto.toGameListItem() =
    GameListItem(
        id = id,
        name = name,
        rating = rating,
        cover = cover,
        platforms = platforms
    )