package com.example.freepsplusgamesnotifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freepsplusgamesnotifier.Screen.Companion.GAME_ID_ARGUMENT
import com.example.freepsplusgamesnotifier.presentation.game_details.components.GameDetailsScreen
import com.example.freepsplusgamesnotifier.presentation.game_list.components.MainScreenList
import com.example.freepsplusgamesnotifier.ui.theme.FreePsPlusGamesNotifierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreePsPlusGamesNotifierTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.GameListScreen.route
                    ) {
                        composable(
                            route = Screen.GameListScreen.route
                        ) {
                            MainScreenList(navController)
                        }
                        composable(
                            route = Screen.GameDetailsScreen.route,
                            arguments = Screen.GameDetailsScreen.arguments
                        ) { backStackEntry ->
                            GameDetailsScreen(
                                backStackEntry.arguments?.getInt(GAME_ID_ARGUMENT)
                            )
                        }

                    }
                }
            }
        }
    }
}

