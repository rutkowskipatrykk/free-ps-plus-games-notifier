package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.data.remote.dto.PlatformType

@Composable
fun PlatformList(
    modifier: Modifier = Modifier,
    platformList: List<PlatformType>?
) {
    Row(modifier = modifier) {
        platformList?.forEach { platform ->
            Image(
                painter = painterResource(id = platform.iconRes),
                "",
                modifier = Modifier
                    .width(42.dp)
                    .height(20.dp)
                    .padding(4.dp),
                contentScale = ContentScale.FillBounds
            )
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
