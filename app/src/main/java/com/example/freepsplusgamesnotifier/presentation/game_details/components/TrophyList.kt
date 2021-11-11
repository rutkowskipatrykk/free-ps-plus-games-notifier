package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyType
import com.example.freepsplusgamesnotifier.domain.model.Trophy

private val padding8dp = 8.dp
private val padding16dp = 16.dp

@Composable
fun TrophyList(
    modifier: Modifier = Modifier,
    onClickShowMoreButton: (() -> Unit)? = null,
    trophies: List<Trophy>?
) {
    trophies?.let {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = R.string.trophies),
                style = MaterialTheme.typography.h2,
            )
            TextButton(onClick = {
                onClickShowMoreButton?.invoke()
            }) {
                Text(stringResource(R.string.show_more))
            }
        }
        LazyRow(modifier = Modifier.padding(bottom = padding16dp)) {
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
    Box(modifier = Modifier.width(96.dp)) {
        Image(
            painter = rememberImagePainter(trophy.image),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = padding16dp)
                .width(64.dp)
                .height(64.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = trophy.getTrophyIcon()),
            "",
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .align(Alignment.TopEnd)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                ),
            contentScale = ContentScale.FillBounds
        )
    }
    Text(text = trophy.name, modifier = Modifier.padding(padding8dp))
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