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

@Composable
fun SingleGameListElement(gameName: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = gameName)
        Text(text = gameName)
    }
}

@Composable
@Preview
fun SingleGamesListElementPreview() {
    SingleGameListElement(gameName = "Test", rememberNavController())
}