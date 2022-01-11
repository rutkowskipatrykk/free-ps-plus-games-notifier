package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING

@Composable
fun HorizontalGameTile(
    gameName: String,
    gameRating: Double,
    gameCover: String?,
    modifier: Modifier = Modifier,
    onClickTile: (() -> Unit)? = null
) {
    Box(
        modifier
            .fillMaxWidth()
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.Center)
                .clickable {
                    onClickTile?.invoke()
                }
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 160.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = gameName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                ) {
                    if (gameRating > 0) {
                        RateArc(gameRating.toInt(), MaterialTheme.colors.surface)
                    }
                }
            }
        }

        Card(
            Modifier
                .width(150.dp)
                .height(220.dp)
                .align(Alignment.CenterStart)
                .shadow(16.dp)
                .clickable {
                    onClickTile?.invoke()
                },
            shape = MaterialTheme.shapes.large,
            elevation = 8.dp,
        ) {
            gameCover?.replace("//images", "https://images").let {
                Image(
                    painter = rememberImagePainter(it),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = EMPTY_STRING,
                    contentScale = ContentScale.FillBounds
                )
            }
        }

    }
}

@Preview
@Composable
fun HorizontalGameTilePreview() {
    HorizontalGameTile("name", 59.0, "")
}
