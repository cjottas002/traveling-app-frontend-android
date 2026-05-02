package org.example.travelingapp.ui.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.travelingapp.ui.theme.Alpha
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelingAppTheme

/**
 * Visual style for a [TravelCard].
 *
 * - [Hairline] (default): bone surface with 1 dp ink-12 border, no shadow.
 *   The Meridian default — flat, editorial, all weight on content.
 * - [Translucent]: bone with alpha, sits on top of hero photos (login, hero details).
 * - [Elevated]: bone with shadow elevation, used for floating cards over feeds.
 */
enum class TravelCardStyle {
    Hairline,
    Translucent,
    Elevated
}

/**
 * Container card. Default is the Meridian hairline look — no shadow, just a
 * thin ink outline at 12% on the bone surface. Use [Translucent] over hero
 * photos and [Elevated] when a card needs to float over a busy feed.
 */
@Composable
fun TravelCard(
    modifier: Modifier = Modifier,
    style: TravelCardStyle = TravelCardStyle.Hairline,
    cornerRadius: Dp = Dimens.radiusMd,
    contentPadding: Dp = Dimens.cardPadding,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    val containerColor = when (style) {
        TravelCardStyle.Hairline -> MaterialTheme.colorScheme.surface
        TravelCardStyle.Translucent -> MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
        TravelCardStyle.Elevated -> MaterialTheme.colorScheme.surface
    }
    val tonalElevation = when (style) {
        TravelCardStyle.Hairline -> Dimens.elevationNone
        TravelCardStyle.Translucent -> Dimens.elevationMd
        TravelCardStyle.Elevated -> Dimens.elevationNone
    }
    val shadowElevation = when (style) {
        TravelCardStyle.Hairline -> Dimens.elevationNone
        TravelCardStyle.Translucent -> Dimens.elevationNone
        TravelCardStyle.Elevated -> Dimens.elevationLg
    }
    val border = if (style == TravelCardStyle.Hairline) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = Alpha.divider))
    } else {
        null
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = containerColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border
    ) {
        Column(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Preview(showBackground = true, name = "Hairline (default)")
@Composable
private fun TravelCardHairlinePreview() {
    TravelingAppTheme {
        TravelCard(modifier = Modifier.padding(Dimens.screenPadding)) {
            TravelText(text = "Hairline card")
            TravelVerticalSpacer(Dimens.cardSpacing)
            TravelText(text = "Default Meridian look — no shadow, hairline ink border.")
        }
    }
}

@Preview(showBackground = true, name = "Translucent")
@Composable
private fun TravelCardTranslucentPreview() {
    TravelingAppTheme {
        TravelCard(
            style = TravelCardStyle.Translucent,
            modifier = Modifier.padding(Dimens.screenPadding)
        ) {
            TravelText(text = "Translucent card")
            TravelVerticalSpacer(Dimens.cardSpacing)
            TravelText(text = "Sits on top of hero images.")
        }
    }
}

@Preview(showBackground = true, name = "Elevated")
@Composable
private fun TravelCardElevatedPreview() {
    TravelingAppTheme {
        TravelCard(
            style = TravelCardStyle.Elevated,
            modifier = Modifier.padding(Dimens.screenPadding)
        ) {
            TravelText(text = "Elevated card")
            TravelVerticalSpacer(Dimens.cardSpacing)
            TravelText(text = "For floating cards in lists.")
        }
    }
}
