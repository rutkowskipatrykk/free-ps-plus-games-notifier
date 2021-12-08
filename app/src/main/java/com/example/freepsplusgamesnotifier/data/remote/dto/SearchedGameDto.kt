package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.SearchedGame
import java.util.*

data class SearchedGameDto(
    val id: Int,
    val name: String,
    val rating: Double,
    val cover: String?,
    val platforms: String,
    val startDay: Date,
    val endDate: Date
)

fun SearchedGameDto.toSearchedGame() =
    SearchedGame(
        id = id,
        name = name,
        rating = rating,
        cover = cover,
        platforms = platforms,
        startDay = startDay,
        endDate = endDate
    )