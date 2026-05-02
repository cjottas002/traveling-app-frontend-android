package org.example.travelingapp.ui.views.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

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
        title = {
            TravelText(
                textRes = titleRes,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            TravelText(
                textRes = textRes,
                style = MaterialTheme.typography.bodyMedium
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
