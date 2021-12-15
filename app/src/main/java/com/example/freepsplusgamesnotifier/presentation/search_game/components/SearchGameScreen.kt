package com.example.freepsplusgamesnotifier.presentation.search_game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.Screen
import com.example.freepsplusgamesnotifier.common.BaseToolbarScreen
import com.example.freepsplusgamesnotifier.domain.model.SearchedGame
import com.example.freepsplusgamesnotifier.presentation.error_screen.ErrorScreen
import com.example.freepsplusgamesnotifier.presentation.game_list.components.HorizontalGameTile
import com.example.freepsplusgamesnotifier.presentation.loading_screen.components.LoadingScreen
import com.example.freepsplusgamesnotifier.presentation.search_game.view_model.SearchGameViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SearchGameScreen(
    navController: NavHostController? = null,
    viewModel: SearchGameViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    BaseToolbarScreen(
        title = "Search for games",
        navigator = navController
    )
    {
        val searchedPhrase = remember { mutableStateOf(viewModel.phrase) }
        Column(
            Modifier
                .fillMaxSize()
        ) {
            SearchBar(searchedValue = searchedPhrase.value) {
                searchedPhrase.value = it
                viewModel.insertGame(it)
            }
            when {
                state.isLoading -> {
                    LoadingScreen()
                }
                state.error?.isNotEmpty() == true -> {
                    ErrorScreen(state.error)
                }
                state.data != null -> {
                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {
                        state.data.groupBy { it.startDay }.forEach {
                            GamesInDate(it) { gameId ->
                                navController?.navigate(
                                    Screen.GameDetailsScreen.generateGameDetailsPath(
                                        gameId
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GamesInDate(gameList: Map.Entry<Date, List<SearchedGame>>, onClickGame: (gameId: Int) -> Unit) {
    val sdf = SimpleDateFormat("MMMM YYYY")
    Text(
        text = sdf.format(gameList.key),
        modifier = Modifier.padding(vertical = 8.dp),
        style = MaterialTheme.typography.h6
    )
    Column {
        gameList.value.forEach { game ->
            HorizontalGameTile(
                game.name,
                game.rating,
                game.cover,
                modifier = Modifier.padding(16.dp)
            ) {
                onClickGame.invoke(game.id)
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchedValue: String,
    onSearchedGamesChanged: ((String) -> Unit)
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
            backgroundColor = Color(232, 232, 232),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(searchedValue = "", onSearchedGamesChanged = {})
}