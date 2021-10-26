package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.Screen
import com.example.freepsplusgamesnotifier.domain.model.GameListItem
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
            Header()
            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                ListContent(viewModel.gameListState.value, navController)
            }
        }
    }
}

@Composable
fun Header(viewModel: GameListViewModel = hiltViewModel()) {
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
        SearchBar(
            viewModel.searchedGame.value,
            { viewModel.searchedGame.value = it }
        )
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
fun ListContent(state: GameListState, navController: NavController) {
    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.error?.isNotEmpty() == true -> {
        }
        state.data != null -> {
            Column {
                GameList(
                    state.data,
                    stringResource(id = R.string.right_now_you_can_get),
                    navController
                )
            }
        }
        else -> {

        }
    }
}

@Composable
fun SearchBar(
    searchedValue: String,
    onSearchedGamesChanged: ((String) -> Unit),
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchedValue,
        onValueChange = onSearchedGamesChanged,
        shape = MaterialTheme.shapes.medium,
        leadingIcon = { Icon(Icons.Rounded.Search, null) },
        label = {
            Text(stringResource(id = R.string.check_was_game_available))
        },
        modifier = modifier
            .fillMaxWidth(),
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun GameList(
    games: List<GameListItem>,
    listTitle: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    if (listTitle.isNotBlank()) {
        Text(text = listTitle, style = MaterialTheme.typography.h1)
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(games) { game ->
            GameTile(game, Modifier.padding(16.dp)) {
                navController.navigate(
                    Screen.GameDetailsScreen.generateGameDetailsPath(
                        game.id
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar("", {})
}

@Preview
@Composable
fun MonthChooserPreview() {
    MonthChooser("Test")
}