package com.example.freepsplusgamesnotifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.freepsplusgamesnotifier.presentation.game_list.GameListState
import com.example.freepsplusgamesnotifier.presentation.game_list.components.SingleGameListElement
import com.example.freepsplusgamesnotifier.presentation.game_list.view_model.GameListViewModel
import com.example.freepsplusgamesnotifier.ui.theme.FreePsPlusGamesNotifierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreePsPlusGamesNotifierTheme {
                val viewModel: GameListViewModel = hiltViewModel()
                viewModel.getGameListForDate(1338933600005)
                Surface(color = MaterialTheme.colors.background) {
                    MainScreenList(viewModel.gameListState)
                }
            }
        }
    }
}

@Composable
fun MainScreenList(state: State<GameListState>) {
    val navController = rememberNavController()
        when {
            state.value.isLoading -> {
                CircularProgressIndicator()
            }
            state.value.error?.isNotEmpty() == true -> {
            }
            state.value.data?.isNotEmpty() == true -> {
                    LazyColumn {
                        this.items(state.value.data!!) { singleGame ->
                            SingleGameListElement(gameName = singleGame.name, navController = navController)
                        }
                    }
            }
        }

}