package org.example.travelingapp.ui.views.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.example.travelingapp.core.ui.R as CoreR
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelingAppTheme

/**
 * Underline-only text field. Container is transparent, the only chrome is the
 * indicator line (1 dp ink unfocused → 2 dp ember focused). Label uses
 * `labelMedium` (mono uppercase, wide tracking) to act as the meta-layer.
 */
@Composable
fun TravelTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = when {
        isPassword && !passwordVisible -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    val trailing: (@Composable () -> Unit)? = when {
        isPassword -> {
            {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisible) CoreR.drawable.ic_visibility else CoreR.drawable.ic_visibility_off
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                )
            }
        }
        trailingIconRes != null -> {
            {
                Icon(
                    painter = painterResource(id = trailingIconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        else -> null
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        readOnly = readOnly,
        label = {
            Text(
                text = stringResource(labelRes),
                style = MaterialTheme.typography.labelMedium
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RectangleShape,
        leadingIcon = leadingIconRes?.let { iconRes ->
            {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingIcon = trailing,
        colors = TextFieldDefaults.colors(
            focusedContainerColor   = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
            disabledContainerColor  = androidx.compose.ui.graphics.Color.Transparent,
            errorContainerColor     = androidx.compose.ui.graphics.Color.Transparent,
            focusedIndicatorColor   = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            disabledIndicatorColor  = MaterialTheme.colorScheme.outlineVariant,
            focusedLabelColor       = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor     = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor             = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true, name = "Text fields")
@Composable
private fun TravelTextFieldPreview() {
    TravelingAppTheme {
        var user by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("Admin123!") }
        Column(modifier = Modifier.padding(Dimens.screenPadding)) {
            TravelTextField(
                value = user,
                onValueChange = { user = it },
                labelRes = android.R.string.search_go
            )
            Spacer(Modifier.padding(Dimens.spacingSm))
            TravelTextField(
                value = pass,
                onValueChange = { pass = it },
                labelRes = android.R.string.unknownName,
                isPassword = true
            )
        }
    }
}
