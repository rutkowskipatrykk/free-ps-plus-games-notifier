package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.presentation.game_list.view_model.GameListViewModel

@Composable
fun MainScreenList(
    navController: NavController,
    viewModel: GameListViewModel = hiltViewModel()
) {
    when {
        viewModel.gameListState.value.isLoading -> {
            CircularProgressIndicator()
        }
        viewModel.gameListState.value.error?.isNotEmpty() == true -> {
        }
        else -> {
            Column {
                Row {
                    Button(onClick = { viewModel.subtractMonth() }) {
                        Text(stringResource(id = R.string.subtract_month_on_list))
                    }
                    Button(onClick = { viewModel.addMonth() }) {
                        Text(stringResource(id = R.string.add_month_on_list))
                    }
                }
                Text(text = viewModel.convertIntDateToString())
                LazyColumn {
                    viewModel.gameListState.value.data?.let {
                        this.items(it) { singleGame ->
                            SingleGameListElement(
                                gameListItem = singleGame,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

}