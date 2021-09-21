package com.example.freepsplusgamesnotifier.presentation.game_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freepsplusgamesnotifier.Screen
import com.example.freepsplusgamesnotifier.domain.model.GameListItem

@Composable
fun SingleGameListElement(gameListItem: GameListItem, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.GameDetailsScreen.generateGameDetailsPath(gameListItem.id))
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = gameListItem.name)
        Text(text = gameListItem.name)
    }
}

@Composable
@Preview
fun SingleGamesListElementPreview() {
    SingleGameListElement(
        gameListItem = GameListItem(0, "TestowaGra", "", ""),
        rememberNavController()
    )
}