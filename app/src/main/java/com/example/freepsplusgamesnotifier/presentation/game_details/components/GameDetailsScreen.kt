package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.common.BaseToolbarScreen
import com.example.freepsplusgamesnotifier.domain.model.Game
import com.example.freepsplusgamesnotifier.domain.model.GameDetailsData
import com.example.freepsplusgamesnotifier.presentation.game_details.view_model.GameDetailsViewModel
import java.lang.Float.min

private val paddingForCoverHeader = 166.dp
private val padding8dp = 8.dp

@Composable
fun GameDetailsScreenInit(
    gameId: Int?,
    navigator: NavHostController,
    gameDetailsViewModel: GameDetailsViewModel = hiltViewModel()
) {
    gameId?.let {
        gameDetailsViewModel.getDetails(gameId)
        GameScreen(navigator, gameDetailsViewModel)
    }
}

@Composable
fun GameScreen(
    navigator: NavHostController,
    gameDetailsViewModel: GameDetailsViewModel = hiltViewModel()
) {
    val gameState = gameDetailsViewModel.gameState.value
    when {
        gameState.isDownloading -> {
            CircularProgressIndicator()
        }
        gameState.error?.isNotEmpty() == true -> {
            Box {
                Text(text = "Error")
            }
        }
        gameState.data != null -> {
            BaseToolbarScreen(navigator, gameState.data.game.name) {
                GameDetailsContent(gameState.data)
            }
        }
    }
}

@Composable
fun GameDetailsContent(gameDetails: GameDetailsData) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column {
            DetailsHeader(
                gameDetails.game,
                Modifier
                    .graphicsLayer {
                        alpha = min(1f, 1 - (scrollState.value / 600f))
                        translationY = -scrollState.value * 0.1f
                    }
            )
            GameDetailsCard(
                gameDetails,
                Modifier
                    .fillMaxHeight()
                    .defaultMinSize(minHeight = LocalConfiguration.current.screenHeightDp.dp - 55.dp)
            )
        }
        HeaderGameCover(gameDetails.game, Modifier
            .padding(top = 30.dp, start = padding8dp)
            .width(150.dp)
            .height(230.dp)
            .graphicsLayer {
                alpha = min(1f, 1 - (scrollState.value / 600f))
                translationY = -scrollState.value * 0.1f
            })
    }
}

@Composable
fun HeaderGameCover(game: Game, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            painter = rememberImagePainter(
                game.cover?.replace(
                    "//images",
                    "https://images"
                )
            ),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun DetailsHeader(game: Game, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.details_background),
            contentDescription = "",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = paddingForCoverHeader, end = padding8dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.h1,
                color = Color.White
            )
            game.developer?.isNotEmpty()?.let {
                Text(
                    text = game.developer,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            game.genre?.isNotEmpty()?.let {
                Text(
                    text = game.genre,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.padding(bottom = padding8dp))
        }
    }
}

@Composable
fun GameDetailsCard(
    gameDetails: GameDetailsData,
    modifier: Modifier = Modifier,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column {
            SocialRow(
                Modifier
                    .fillMaxWidth()
                    .padding(start = paddingForCoverHeader, top = padding8dp, end = padding8dp),
                viewModel::searchInGoogle,
                viewModel::searchInYoutube,
                viewModel::searchInPsStore,
                viewModel::searchInMetacritic,
            )
            PlatformList(
                Modifier.padding(start = padding8dp, top = padding8dp),
                viewModel.platformList
            )
            gameDetails.game.summary?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(padding8dp)
                )
            }
            if (gameDetails.trophies != null && gameDetails.trophies.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.trophies),
                        style = MaterialTheme.typography.h2,
                    )
                    TextButton(onClick = {
                        viewModel.changeListDirection()
                    }) {
                        Text(stringResource(id = viewModel.getButtonText()))
                    }
                }
                if (gameDetails.trophies.isNotEmpty()) {
                    if (viewModel.isListHorizontal.value) {
                        HorizontalTrophyList(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding8dp),
                            gameDetails.trophies
                        )
                    } else {
                        VerticalTrophyList(trophies = gameDetails.trophies)
                    }
                }
            }
        }
    }
}
