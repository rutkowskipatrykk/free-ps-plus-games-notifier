package com.example.freepsplusgamesnotifier.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Colors.chipTextColor: Color
    get() = if(isLight) whiteSmoke else nightRider

val Colors.headerTextColor: Color
    get() = if (isLight) whiteSmoke else nightRider

val Colors.toolbarElementsColor: Color
    get() = if (isLight) whiteSmoke else nightRider

val Colors.buttonColor: Color
    get() = if (isLight) freeSpeechBlue else mortar