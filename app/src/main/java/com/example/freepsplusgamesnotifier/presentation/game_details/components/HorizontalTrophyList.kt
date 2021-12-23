package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyType
import com.example.freepsplusgamesnotifier.domain.model.Trophy

@Composable
fun HorizontalTrophyList(
    modifier: Modifier = Modifier,
    trophies: List<Trophy>?
) {
    trophies?.let {
        LazyRow(modifier = modifier.padding(bottom = 16.dp)) {
            items(trophies) {
                Column(
                    modifier = Modifier.width(128.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SingleTrophy(it)
                }
            }
        }
    }
}

@Composable
fun SingleTrophy(trophy: Trophy) {
    TrophyWithTrophyIcon(trophy = trophy, iconBackground = MaterialTheme.colors.background)
    Text(
        text = trophy.name,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview
@Composable
fun SingleBronzeTrophyPreview() {
    SingleTrophy(Trophy("description", "", "bronze", TrophyType.BRONZE))
}

@Preview
@Composable
fun SingleSilverTrophyPreview() {
    SingleTrophy(Trophy("description", "", "bronze", TrophyType.SILVER))
}

@Preview
@Composable
fun SingleGoldTrophyPreview() {
    SingleTrophy(Trophy("description", "", "bronze", TrophyType.GOLD))
}

@Preview
@Composable
fun SinglePlatinumTrophyPreview() {
    SingleTrophy(Trophy("description", "", "bronze", TrophyType.PLATINUM))
}