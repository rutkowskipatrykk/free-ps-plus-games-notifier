package com.example.freepsplusgamesnotifier.presentation.game_details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freepsplusgamesnotifier.R

private val padding8dp = 8.dp

@Composable
fun SocialRow(
    modifier: Modifier = Modifier,
    onGoogleClick: (() -> Unit)? = null,
    onYoutubeClick: (() -> Unit)? = null,
    onPsStoreClick: (() -> Unit)? = null,
    onMetacriticClick: (() -> Unit)? = null,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        SocialIcon(R.drawable.google_logo) { onGoogleClick?.invoke() }
        SocialIcon(R.drawable.yt_logo) { onYoutubeClick?.invoke() }
        SocialIcon(R.drawable.ps_store_logo) { onPsStoreClick?.invoke() }
        SocialIcon(R.drawable.metacritic_logo) { onMetacriticClick?.invoke() }
    }
}

@Composable
fun SocialIcon(@DrawableRes imageId: Int, onClick: (() -> Unit)) {
    Box(modifier = Modifier.clickable {
        onClick.invoke()
    }) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .padding(bottom = padding8dp)
        )
    }
}

@Preview
@Composable
fun SocialRowPreview() {
    SocialRow()
}