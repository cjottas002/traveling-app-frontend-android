package org.example.travelingapp.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelingAppTheme

/**
 * Three-line editorial block used at the top of every Meridian screen with
 * voice (Login, Register, Onboarding, Home, Hotel detail, …).
 *
 *   ─── KICKER (mono ember + 16dp ember rule)
 *   Display headline
 *   *italic ember accent*
 *   Sub muted (optional)
 *
 * `kicker` is rendered uppercase automatically. `accent` and `sub` are optional.
 * `headlineColor` defaults to onBackground but can be overridden when the
 * block sits on a hero (passing white/bone for legibility).
 */
@Composable
fun TravelEditorialBlock(
    kicker: String,
    title: String,
    modifier: Modifier = Modifier,
    accent: String? = null,
    sub: String? = null,
    headlineColor: Color = MaterialTheme.colorScheme.onBackground,
    accentColor: Color = MaterialTheme.colorScheme.secondary,
    subColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(1.dp)
                    .background(accentColor)
            )
            Spacer(Modifier.width(Dimens.spacingSm))
            Text(
                text = kicker.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = accentColor
            )
        }
        Spacer(Modifier.height(Dimens.spacingSm))
        Text(
            text = headlineWithAccent(title, accent, accentColor),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Medium,
                color = headlineColor
            )
        )
        if (!sub.isNullOrBlank()) {
            Spacer(Modifier.height(Dimens.spacingMd))
            Text(
                text = sub,
                style = MaterialTheme.typography.bodyMedium,
                color = subColor
            )
        }
    }
}

private fun headlineWithAccent(
    title: String,
    accent: String?,
    accentColor: Color
): AnnotatedString = buildAnnotatedString {
    append(title)
    if (!accent.isNullOrBlank()) {
        append('\n')
        withStyle(
            SpanStyle(
                color = accentColor,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal
            )
        ) { append(accent) }
    }
}

@Preview(showBackground = true, name = "Editorial block")
@Composable
private fun TravelEditorialBlockPreview() {
    TravelingAppTheme {
        TravelEditorialBlock(
            kicker = "Acceder",
            title = "Bienvenido",
            accent = "de vuelta.",
            sub = "Continúa donde lo dejaste. Tus reservas, sincronizadas.",
            modifier = Modifier.padding(Dimens.screenPadding)
        )
    }
}
