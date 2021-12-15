package com.example.freepsplusgamesnotifier.presentation.error_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING

@Composable
fun ErrorScreen(errorText: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Image(painter = painterResource(id = R.drawable.controller), contentDescription = EMPTY_STRING)
            Text(
                text = errorText,
                style = MaterialTheme.typography.h2
            )
        }
    }
}