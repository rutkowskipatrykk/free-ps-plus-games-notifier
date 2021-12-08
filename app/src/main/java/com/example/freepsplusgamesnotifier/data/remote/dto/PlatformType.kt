package com.example.freepsplusgamesnotifier.data.remote.dto

import androidx.annotation.DrawableRes
import com.example.freepsplusgamesnotifier.R
import com.google.gson.annotations.SerializedName

enum class PlatformType(val platformTitle: String) {
    @SerializedName("PSP")
    PSP("PSP"),

    @SerializedName("PS3")
    PS3("PS3"),

    @SerializedName("PS4")
    PS4("PS4"),

    @SerializedName("PS5")
    PS5("PS5"),

    @SerializedName("VITA")
    VITA("VITA"),

    @SerializedName("PS_TV")
    PS_TV("PS_TV"),

    @SerializedName("PS_VR")
    PS_VR("PS_VR")
}