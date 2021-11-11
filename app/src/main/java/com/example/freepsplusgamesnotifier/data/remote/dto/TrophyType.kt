package com.example.freepsplusgamesnotifier.data.remote.dto

import com.google.gson.annotations.SerializedName

enum class TrophyType {
    @SerializedName("BRONZE")
    BRONZE,
    @SerializedName("SILVER")
    SILVER,
    @SerializedName("GOLD")
    GOLD,
    @SerializedName("PLATINUM")
    PLATINUM;
}