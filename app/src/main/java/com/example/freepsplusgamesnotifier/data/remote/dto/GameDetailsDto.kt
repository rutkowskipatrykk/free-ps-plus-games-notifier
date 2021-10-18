package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.Game

data class GameDetailsDto(
    val id: Int,
    val developer: String?,
    val genre: String?,
    val name: String,
    val platforms: String?,
    val summary: String?,
    val timeToBeat: String?,
    val rating: Double?,
    val cover: String?
)

fun GameDetailsDto.toGame() =
    Game(
        id = id,
        developer = developer,
        genre = genre,
        name = name,
        platforms = platforms,
        summary = summary,
        timeToBeat = timeToBeat,
        rating = rating,
        cover = cover
    )