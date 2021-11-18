package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyType
import com.example.freepsplusgamesnotifier.domain.model.Trophy

@Composable
fun VerticalTrophyList(modifier: Modifier = Modifier, trophies: List<Trophy>?) {
    Column(modifier = modifier) {
        trophies?.forEach {
            VerticalTrophyElement(it)
        }
    }
}

@Composable
fun VerticalTrophyElement(trophy: Trophy) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            TrophyWithTrophyIcon(trophy = trophy, iconBackground = Color.White)
            Column {
                Text(text = trophy.name, fontWeight = FontWeight.Bold)
                Text(text = trophy.description, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

@Preview
@Composable
fun VerticalPlatinumTrophyElementPreview() {
    VerticalTrophyElement(
        Trophy(
            "description description description description description description description description description description description description description description description description description description ",
            "",
            "bronze",
            TrophyType.PLATINUM
        )
    )
}


@Preview
@Composable
fun VerticalGoldTrophyElementPreview() {
    VerticalTrophyElement(
        Trophy(
            "description description description description description description description description description description description description description description description description description description ",
            "",
            "bronze",
            TrophyType.GOLD
        )
    )
}


@Preview
@Composable
fun VerticalSilverTrophyElementPreview() {
    VerticalTrophyElement(
        Trophy(
            "description description description description description description description description description description description description description description description description description description ",
            "",
            "bronze",
            TrophyType.SILVER
        )
    )
}

@Preview
@Composable
fun VerticalBronzeTrophyElementPreview() {
    VerticalTrophyElement(
        Trophy(
            "description description description description description description description description description description description description description description description description description description ",
            "",
            "bronze",
            TrophyType.BRONZE
        )
    )
}