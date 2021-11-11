package com.example.freepsplusgamesnotifier.domain.model

data class GameDetailsData(
    val game: Game,
    val trophies: List<Trophy>?
)