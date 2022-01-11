package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.data.remote.dto.PlatformType

@Composable
fun PlatformList(
    modifier: Modifier = Modifier
        .background(MaterialTheme.colors.background),
    platformList: List<PlatformType>?
) {
    Row(modifier = modifier.fillMaxWidth()) {
        platformList?.forEach { platform ->
            Chip(chipText = platform.platformTitle, modifier = Modifier.padding(end = 8.dp))
        }
    }
}

@Preview
@Composable
fun PlatformList() {
    PlatformList(
        platformList = listOf(
            PlatformType.PS3,
            PlatformType.PS4,
            PlatformType.PS5,
            PlatformType.PSP,
            PlatformType.PS_TV,
            PlatformType.PS_VR,
            PlatformType.VITA
        )
    )
}
