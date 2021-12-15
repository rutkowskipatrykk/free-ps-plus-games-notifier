package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.domain.model.Trophy

private val padding16dp = 16.dp

@Composable
fun TrophyWithTrophyIcon(modifier: Modifier = Modifier, trophy: Trophy, iconBackground: Color) {
    Box(modifier = modifier.width(96.dp)) {
        Image(
            painter = rememberImagePainter(trophy.image),
            contentDescription = EMPTY_STRING,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = padding16dp)
                .width(64.dp)
                .height(64.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = trophy.getTrophyIcon()),
            EMPTY_STRING,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .align(Alignment.TopEnd)
                .background(
                    color = iconBackground,
                    shape = MaterialTheme.shapes.large
                ),
            contentScale = ContentScale.FillBounds
        )
    }
}
