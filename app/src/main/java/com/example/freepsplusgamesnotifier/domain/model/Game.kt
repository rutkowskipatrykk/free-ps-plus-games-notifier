package com.example.freepsplusgamesnotifier.domain.model

import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto

data class Game (
    val id : Int,
    val developer : String,
    val genre : String,
    val name : String,
    val platforms : String,
    val summary : String,
    val timeToBeat : String,
    val rating : Double,
    val cover : String,
    val trophies : List<TrophyDto>
)