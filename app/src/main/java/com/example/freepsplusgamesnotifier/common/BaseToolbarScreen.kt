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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.ui.theme.toolbarElementsColor

@Composable
fun BaseToolbarScreen(
    navigator: NavHostController? = null,
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                title = { Text(title, color = MaterialTheme.colors.toolbarElementsColor) },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = EMPTY_STRING,
                        modifier = Modifier.clickable {
                            navigator?.popBackStack()
                        },
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.toolbarElementsColor)
                    )
                })
        }
    ) {
        content.invoke(PaddingValues())
    }
}