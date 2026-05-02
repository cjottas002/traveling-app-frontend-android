package org.example.travelingapp.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelingAppTheme

/**
 * Sizes of the [TravelBrandMark] lockup.
 *
 * - [Small] for in-screen headers (Login, app bars).
 * - [Medium] for decorative use in editorial blocks.
 * - [Large] for splash and brand reveals.
 */
enum class BrandMarkSize { Small, Medium, Large }

/**
 * Travel*ing* lockup — ember accent square + wordmark with italic ember "ing".
 * Used by Splash, Login header and any place the brand needs to be re-stated.
 *
 * Honours the surrounding text color via [color]: pass bone for dark surfaces,
 * the default ink for light surfaces.
 */
@Composable
fun TravelBrandMark(
    modifier: Modifier = Modifier,
    size: BrandMarkSize = BrandMarkSize.Small,
    color: Color = MaterialTheme.colorScheme.onBackground,
    accent: Color = MaterialTheme.colorScheme.secondary
) {
    val (dot, font, gap) = when (size) {
        BrandMarkSize.Small  -> Triple(8.dp,  20.sp, Dimens.spacingSm)
        BrandMarkSize.Medium -> Triple(10.dp, 28.sp, Dimens.spacingSm)
        BrandMarkSize.Large  -> Triple(12.dp, 44.sp, Dimens.spacingMd)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .size(dot)
                .background(accent)
        )
        Spacer(Modifier.width(gap))
        Text(
            text = wordmark(accent),
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = font,
                lineHeight = font,
                fontWeight = FontWeight.Medium,
                color = color
            )
        )
    }
}

private fun wordmark(accent: Color): AnnotatedString = buildAnnotatedString {
    append("Travel")
    withStyle(
        SpanStyle(
            color = accent,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal
        )
    ) { append("ing") }
}

@Preview(showBackground = true, name = "Brand mark sizes")
@Composable
private fun TravelBrandMarkPreview() {
    TravelingAppTheme {
        androidx.compose.foundation.layout.Column(
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(Dimens.spacingLg),
            modifier = Modifier.padding(Dimens.screenPadding)
        ) {
            TravelBrandMark(size = BrandMarkSize.Small)
            TravelBrandMark(size = BrandMarkSize.Medium)
            TravelBrandMark(size = BrandMarkSize.Large)
        }
    }
}
