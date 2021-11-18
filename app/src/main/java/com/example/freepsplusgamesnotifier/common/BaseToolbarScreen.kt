package com.example.freepsplusgamesnotifier.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.freepsplusgamesnotifier.R

@Composable
fun BaseToolbarScreen(
    navigator: NavHostController? = null,
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(title) },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navigator?.popBackStack()
                        }
                    )
                })
        }
    ) {
        content.invoke(PaddingValues())
    }
}