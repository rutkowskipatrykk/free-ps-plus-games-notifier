package com.example.freepsplusgamesnotifier.domain.model

import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyType

data class Trophy(
    val description: String,
    val image: String?,
    val name: String,
    val type: TrophyType
) {

    fun getTrophyIcon() = when (type) {
        TrophyType.BRONZE -> R.drawable.ps5_bronze_trophy
        TrophyType.SILVER -> R.drawable.ps5_silver_trophy
        TrophyType.GOLD -> R.drawable.ps5_gold_trophy
        TrophyType.PLATINUM -> R.drawable.ps5_platinum_trophy
    }

}