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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.domain.model.GameListItem

@Composable
fun VerticalGameTile(
    game: GameListItem,
    modifier: Modifier = Modifier,
    onClickTile: (() -> Unit)? = null
) {
    Box(
        Modifier
            .width(190.dp)
            .height(330.dp)
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    onClickTile?.invoke()
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                ) {
                    if (game.rating > 0) {
                        RateArc(game.rating.toInt(), Color.White)
                    }
                    Text(
                        text = game.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
        Card(
            modifier
                .width(150.dp)
                .height(220.dp)
                .align(Alignment.TopCenter)
                .clickable {
                    onClickTile?.invoke()
                },
            shape = MaterialTheme.shapes.large,
            elevation = 8.dp,
        ) {
            game.cover?.replace("//images", "https://images").let {
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
fun VerticalGameTilePreview() {
    GameTile(GameListItem.getMockItem())
}
