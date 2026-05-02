package org.example.travelingapp.ui.views.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.travelingapp.ui.theme.Dimens

/**
 * Confirmation dialog. Title in display headline, body in bodyMedium, confirm
 * button in ember (TravelTextButton) and dismiss in ink (TravelTextButton with
 * `onSurfaceVariant` color).
 *
 * Note: the Meridian doc proposes a bottom-sheet variant for richer dialogs,
 * but for simple confirm/cancel flows AlertDialog is the right primitive.
 * Will introduce a `TravelSheetDialog` later if a screen needs it.
 */
@Composable
fun TravelDialog(
    onDismissRequest: () -> Unit,
    @StringRes titleRes: Int,
    @StringRes textRes: Int,
    @StringRes confirmTextRes: Int,
    onConfirm: () -> Unit,
    @StringRes dismissTextRes: Int? = null,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(Dimens.radiusMd),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = AlertDialogDefaults.TonalElevation,
        title = {
            TravelText(
                textRes = titleRes,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            TravelText(
                textRes = textRes,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            TravelTextButton(textRes = confirmTextRes, onClick = onConfirm)
        },
        dismissButton = if (dismissTextRes != null && onDismiss != null) {
            { TravelTextButton(textRes = dismissTextRes, onClick = onDismiss) }
        } else null
    )
}
