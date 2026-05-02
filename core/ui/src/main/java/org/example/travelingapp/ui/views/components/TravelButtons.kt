package org.example.travelingapp.ui.views.components

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.Easings
import org.example.travelingapp.ui.theme.Motion
import org.example.travelingapp.ui.theme.TravelingAppTheme

/**
 * Tone of a primary CTA. [Ink] is the default editorial CTA used everywhere;
 * [Ember] is reserved for hero contexts (onboarding, splash-adjacent reveals)
 * where the coral accent provides warmth on a dark background.
 */
enum class ButtonTone { Ink, Ember }

/**
 * Primary editorial CTA. 4 dp radius (no pastilla), label in mono uppercase.
 * Press triggers a 0.96 scale micro-interaction (Meridian signature).
 */
@Composable
fun TravelPrimaryButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tone: ButtonTone = ButtonTone.Ink,
    trailingArrow: Boolean = false
) {
    val container = when (tone) {
        ButtonTone.Ink -> MaterialTheme.colorScheme.primary
        ButtonTone.Ember -> MaterialTheme.colorScheme.secondary
    }
    val content = when (tone) {
        ButtonTone.Ink -> MaterialTheme.colorScheme.onPrimary
        ButtonTone.Ember -> MaterialTheme.colorScheme.onSecondary
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = Motion.short, easing = Easings.standard),
        label = "primary_press_scale"
    )
    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight)
            .scale(scale),
        shape = RoundedCornerShape(Dimens.radiusXs),
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = content,
            disabledContainerColor = container.copy(alpha = 0.4f),
            disabledContentColor = content.copy(alpha = 0.4f)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.spacingSm)
        ) {
            Text(
                text = stringResource(textRes),
                style = MaterialTheme.typography.labelLarge
            )
            if (trailingArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.height(Dimens.iconSm)
                )
            }
        }
    }
}

/**
 * Outlined CTA with 1.5 dp ink hairline. Used as cancel/secondary action.
 *
 * Set [onDark] when placing the button over a dark hero (onboarding, hero
 * cards) so the border + content swap to bone semi-transparent for
 * legibility against the dark photo.
 */
@Composable
fun TravelSecondaryButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onDark: Boolean = false
) {
    val borderColor = if (onDark) Color.White.copy(alpha = 0.35f) else MaterialTheme.colorScheme.primary
    val contentColor = if (onDark) Color.White.copy(alpha = 0.85f) else MaterialTheme.colorScheme.primary
    val containerColor = if (onDark) Color.Transparent else MaterialTheme.colorScheme.background

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = Motion.short, easing = Easings.standard),
        label = "secondary_press_scale"
    )
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight)
            .scale(scale),
        shape = RoundedCornerShape(Dimens.radiusXs),
        border = BorderStroke(width = 1.5.dp, color = borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = 0.38f)
        )
    ) {
        Text(
            text = stringResource(textRes),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/** Inline text button with ember accent — used for "no", "skip", inline links inside dialogs. */
@Composable
fun TravelTextButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TravelTextButton(
        text = stringResource(textRes),
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun TravelTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true, name = "Buttons")
@Composable
private fun ButtonsPreview() {
    TravelingAppTheme {
        Column(modifier = Modifier.padding(Dimens.screenPadding)) {
            TravelPrimaryButton(textRes = android.R.string.ok, onClick = {})
            Spacer(Modifier.padding(Dimens.spacingSm))
            TravelPrimaryButton(textRes = android.R.string.ok, onClick = {}, tone = ButtonTone.Ember)
            Spacer(Modifier.padding(Dimens.spacingSm))
            TravelPrimaryButton(textRes = android.R.string.ok, onClick = {}, enabled = false)
            Spacer(Modifier.padding(Dimens.spacingSm))
            TravelSecondaryButton(textRes = android.R.string.cancel, onClick = {})
            Spacer(Modifier.padding(Dimens.spacingSm))
            TravelTextButton(textRes = android.R.string.ok, onClick = {})
        }
    }
}
