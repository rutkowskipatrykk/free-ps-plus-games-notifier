package com.example.freepsplusgamesnotifier.domain.model

data class GameListItem (
    val id: Int,
    val name: String,
    val rating: Double,
    val cover: String?,
    val platforms: String
)