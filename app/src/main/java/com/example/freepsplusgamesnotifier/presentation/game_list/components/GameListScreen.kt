package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.Screen
import com.example.freepsplusgamesnotifier.domain.model.GameListItem
import com.example.freepsplusgamesnotifier.domain.model.ListMode
import com.example.freepsplusgamesnotifier.presentation.game_list.GameListState
import com.example.freepsplusgamesnotifier.presentation.game_list.view_model.GameListViewModel

@Composable
fun MainScreenList(
    navController: NavController,
    viewModel: GameListViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colors.primary)
    ) {
        Column {
            Header({
                navController.navigate(Screen.GameSearchScreen.route)
            })
            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                ListContent(viewModel.gameListState.value, {
                    navController.navigate(
                        Screen.GameDetailsScreen.generateGameDetailsPath(
                            it
                        )
                    )
                })
            }
        }
    }
}

@Composable
fun Header(
    onSearchBarClick: (() -> Unit)? = null,
    viewModel: GameListViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MonthChooser(
            viewModel.displayDate.value,
            { viewModel.addMonth() },
            { viewModel.subtractMonth() })
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.background
        )
        Spacer(modifier = Modifier.size(24.dp))
        SearchButton(onSearchBarClick)
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun MonthChooser(
    month: String,
    onAddMonth: (() -> Unit)? = null,
    onSubtractMonth: (() -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            tint = MaterialTheme.colors.background,
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    onSubtractMonth?.invoke()
                })
        Text(
            text = month,
            fontSize = TextUnit.Unspecified,
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(
                Alignment.CenterVertically
            )
        )
        Icon(Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = MaterialTheme.colors.background,
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    onAddMonth?.invoke()
                })
    }
}

@Composable
fun ListContent(
    state: GameListState,
    onClickGame: (id: Int) -> Unit,
    viewModel: GameListViewModel = hiltViewModel()
) {
    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.error?.isNotEmpty() == true -> {
        }
        state.data != null -> {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = viewModel.oppositeListIcon.value),
                            contentDescription = "",
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    viewModel.changeOrientation()
                                }
                        )
                    }
                }
                item {
                    GameList(
                        state.data,
                        onClickGame
                    )
                }
            }
        }
        else -> {

        }
    }
}

@Composable
fun GameList(
    games: List<GameListItem>,
    onClickGame: (id: Int) -> Unit,
    viewModel: GameListViewModel = hiltViewModel()
) {
    if (viewModel.listModeMode.value == ListMode.TILE) {
        VerticalGameList(games, onClickGame)
    } else {
        HorizontalGameList(games, onClickGame)
    }
}

@Composable
fun VerticalGameList(
    games: List<GameListItem>,
    onClickGame: (id: Int) -> Unit
) {
    games.chunked(2).forEach { gameListOfTwo ->
        RowOfTwoGames(gameListOfTwo, onClickGame)
    }
}

@Composable
fun HorizontalGameList(
    games: List<GameListItem>,
    onClickGame: (id: Int) -> Unit
) {
    games.forEach { game ->
        HorizontalGameTile(game.name, game.rating, game.cover, Modifier.padding(16.dp)) {
            onClickGame.invoke(game.id)
        }
    }
}

@Composable
fun RowOfTwoGames(games: List<GameListItem>, onClickGame: (id: Int) -> Unit) {
    Row {
        games.forEach { game ->
            VerticalGameTile(game, Modifier.padding(16.dp)) {
                onClickGame.invoke(game.id)
            }
        }
    }
}

@Composable
fun SearchButton(onSearchBarClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onSearchBarClick?.invoke() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = Icons.Filled.Search,
            contentDescription = "Search"
        )
        Text(
            text = "Check if game was available on PSPlus",
            color = Color.Black
        )
    }
}


@Preview
@Composable
fun MonthChooserPreview() {
    MonthChooser("Test")
}

@Preview
@Composable
fun SearchButtonPreview() {
    SearchButton()
}