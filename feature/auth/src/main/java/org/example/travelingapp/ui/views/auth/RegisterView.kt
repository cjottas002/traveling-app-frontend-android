package org.example.travelingapp.ui.views.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import org.example.travelingapp.feature.auth.R
import org.example.travelingapp.ui.testtags.AuthTestTags
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.auth.viewmodel.AuthViewModel
import org.example.travelingapp.ui.views.components.TravelCheckbox
import org.example.travelingapp.ui.views.components.TravelIconButton
import org.example.travelingapp.ui.views.components.TravelPrimaryButton
import org.example.travelingapp.ui.views.components.TravelTextField
import org.example.travelingapp.ui.views.components.TravelVerticalSpacer

@Composable
fun RegisterView(navController: NavController, onNavigateToLogin: () -> Unit) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val username by authViewModel.username.collectAsState()
    val password by authViewModel.password.collectAsState()
    val confirmPassword by authViewModel.confirmPassword.collectAsState()
    val isRegisterEnabled by authViewModel.isRegisterEnabled.collectAsState()
    val acceptedTerms by authViewModel.acceptedTerms.collectAsState()
    val passwordsMatch by authViewModel.passwordsMatch.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.screenPadding)
            .testTag(AuthTestTags.REGISTER_SCREEN)
    ) {
        Header(onBackClicked = { navController.popBackStack() })

        Spacer(Modifier.padding(top = Dimens.spacingXl))

        EditorialBlock()

        Spacer(Modifier.padding(top = Dimens.sectionSpacing))

        TravelTextField(
            value = username,
            onValueChange = { authViewModel.onUsernameChanged(it) },
            labelRes = R.string.username,
            modifier = Modifier.testTag(AuthTestTags.REGISTER_NAME_FIELD)
        )

        TravelVerticalSpacer(Dimens.spacingMd)

        TravelTextField(
            value = password,
            onValueChange = { authViewModel.onPasswordChanged(it) },
            labelRes = R.string.password,
            isPassword = true,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.testTag(AuthTestTags.REGISTER_LASTNAME_FIELD)
        )

        TravelVerticalSpacer(Dimens.spacingMd)

        TravelTextField(
            value = confirmPassword,
            onValueChange = { authViewModel.onConfirmPasswordChanged(it) },
            labelRes = R.string.confirm_password,
            isPassword = true,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.testTag(AuthTestTags.REGISTER_AGE_FIELD)
        )

        if (confirmPassword.isNotBlank() && !passwordsMatch) {
            TravelVerticalSpacer(Dimens.spacingXs)
            Text(
                text = stringResource(R.string.passwords_dont_match),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        TravelVerticalSpacer(Dimens.spacingLg)

        TermsRow(
            checked = acceptedTerms,
            onCheckedChange = { authViewModel.onTermsChanged(it) }
        )

        TravelVerticalSpacer(Dimens.spacingLg)

        TravelPrimaryButton(
            textRes = R.string.register_action,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(AuthTestTags.REGISTER_SUBMIT_BUTTON),
            enabled = isRegisterEnabled,
            trailingArrow = true,
            onClick = {
                authViewModel.register(
                    onSuccess = { onNavigateToLogin() },
                    onError = { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )

        TravelVerticalSpacer(Dimens.spacingSm)

        Text(
            text = stringResource(R.string.already_have_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToLogin() }
                .padding(vertical = Dimens.spacingSm)
        )

        TravelVerticalSpacer(Dimens.screenBottomPadding)
    }
}

@Composable
private fun Header(onBackClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.spacingSm)
    ) {
        TravelIconButton(
            iconRes = R.drawable.ic_arrow_back,
            contentDescription = null,
            iconTint = MaterialTheme.colorScheme.onBackground,
            onClick = onBackClicked
        )
        Spacer(Modifier.width(Dimens.spacingSm))
        Text(
            text = stringResource(R.string.register_step_meta),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EditorialBlock() {
    val ember = MaterialTheme.colorScheme.secondary
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(1.dp)
                    .background(ember)
            )
            Spacer(Modifier.width(Dimens.spacingSm))
            Text(
                text = stringResource(R.string.register_kicker).uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = ember
            )
        }
        TravelVerticalSpacer(Dimens.spacingSm)
        Text(
            text = headlineWithAccent(
                title = stringResource(R.string.register_title),
                accent = stringResource(R.string.register_title_accent),
                accentColor = ember
            ),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        TravelVerticalSpacer(Dimens.spacingMd)
        Text(
            text = stringResource(R.string.register_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun TermsRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.spacingSm),
        modifier = Modifier.fillMaxWidth()
    ) {
        TravelCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = stringResource(R.string.accept_terms),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun headlineWithAccent(
    title: String,
    accent: String,
    accentColor: Color
): AnnotatedString = buildAnnotatedString {
    append(title)
    append('\n')
    withStyle(
        SpanStyle(
            color = accentColor,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal
        )
    ) { append(accent) }
}
