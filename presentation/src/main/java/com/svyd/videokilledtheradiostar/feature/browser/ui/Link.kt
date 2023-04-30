package com.svyd.videokilledtheradiostar.feature.browser.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.svyd.videokilledtheradiostar.R
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement

@Composable
fun HorizontalLink(element: UiElement, onLinkClick: (url: String, title: String) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .clickable { element.url?.let { onLinkClick(it, element.text) } }
            .padding(0.dp, 0.dp, 16.dp, 0.dp)
    ) {
        LinkBody(element = element, onLinkClick)
    }
}

@Composable
fun VerticalLink(element: UiElement, onLinkClick: (url: String, title: String) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .fillMaxWidth()
    ) {
        LinkBody(element = element, onLinkClick)
    }
}

@Composable
fun LinkBody(element: UiElement, onLinkClick: (url: String, title: String) -> Unit) {
    Row(
        modifier = Modifier.clickable { element.url?.let { onLinkClick(it, element.text) } }
    ) {
        val image = element.playingImage ?: element.image
        LinkCover(image = image, placeholder = R.drawable.folder)

        Column(
            modifier = Modifier
                .padding(0.dp, 16.dp, 16.dp, 16.dp)
                .defaultMinSize(Dp.Unspecified, 40.dp)
                .widthIn(128.dp, 256.dp)
        ) {
            Text(text = element.text)
            element.subtext?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun LinkCover(image: String?, @DrawableRes placeholder: Int) {
    Box(modifier = Modifier.padding(16.dp)) {
        if (image != null) {
            AsyncImage(
                model = image,
                contentDescription = "Cover",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
            )
        } else {
            Image(
                painter = painterResource(id = placeholder),
                contentDescription = "Folder",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
