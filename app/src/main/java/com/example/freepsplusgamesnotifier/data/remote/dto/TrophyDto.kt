package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.Trophy

data class TrophyDto(
    val description: String,
    val image: String,
    val name: String,
    val type: String
)

fun TrophyDto.toTrophy() =
    Trophy(
        description = description,
        image = image,
        name = name,
        type = type,
    )
