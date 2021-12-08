package com.example.freepsplusgamesnotifier.domain.model

import java.util.*

data class SearchedGame (
    val id: Int,
    val name: String,
    val rating: Double,
    val cover: String?,
    val platforms: String,
    val startDay: Date,
    val endDate: Date
)