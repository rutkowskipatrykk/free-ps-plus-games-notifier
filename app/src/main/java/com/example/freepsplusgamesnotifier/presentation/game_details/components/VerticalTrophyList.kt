package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyType
import com.example.freepsplusgamesnotifier.domain.model.Trophy

@Composable
fun VerticalTrophyList(modifier: Modifier = Modifier, trophies: List<Trophy>?) {
    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
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
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrophyWithTrophyIcon(trophy = trophy, iconBackground = MaterialTheme.colors.surface)
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = trophy.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    text = trophy.description,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.body2,
                )
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