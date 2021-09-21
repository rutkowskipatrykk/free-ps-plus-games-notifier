package com.example.freepsplusgamesnotifier

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = listOf()) {

    companion object {
        const val GAME_ID_ARGUMENT = "gameId"
    }

    object GameListScreen : Screen("game_list_screen")
    object GameDetailsScreen : Screen(
        "game_details_screen/{$GAME_ID_ARGUMENT}",
        listOf(navArgument(GAME_ID_ARGUMENT) { type = NavType.IntType })
    ) {
        fun generateGameDetailsPath(gameId: Int) = "game_details_screen/$gameId"
    }

}