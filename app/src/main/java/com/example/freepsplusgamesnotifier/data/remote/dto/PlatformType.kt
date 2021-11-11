package com.example.freepsplusgamesnotifier.data.remote.dto

import androidx.annotation.DrawableRes
import com.example.freepsplusgamesnotifier.R
import com.google.gson.annotations.SerializedName

enum class PlatformType(@DrawableRes val iconRes: Int) {
    @SerializedName("PSP")
    PSP(R.drawable.psp_logo),

    @SerializedName("PS3")
    PS3(R.drawable.ps3_logo),

    @SerializedName("PS4")
    PS4(R.drawable.ps4_logo),

    @SerializedName("PS5")
    PS5(R.drawable.ps5_logo),

    @SerializedName("VITA")
    VITA(R.drawable.psvita_logo),

    @SerializedName("PS_TV")
    PS_TV(R.drawable.ps_tv_logo),

    @SerializedName("PS_VR")
    PS_VR(R.drawable.ps_vr_logo)
}