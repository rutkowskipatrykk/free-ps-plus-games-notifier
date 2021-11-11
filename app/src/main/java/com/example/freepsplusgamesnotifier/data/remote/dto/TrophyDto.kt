package com.example.freepsplusgamesnotifier.data.remote.dto

import com.example.freepsplusgamesnotifier.domain.model.Trophy

data class TrophyDto(
    val description: String,
    val imageSrc: String?,
    val name: String,
    val type: TrophyType
)

fun TrophyDto.toTrophy() =
    Trophy(
        description = description,
        image = imageSrc,
        name = name,
        type = type,
    )
