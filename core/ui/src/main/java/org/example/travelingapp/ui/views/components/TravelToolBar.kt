package org.example.travelingapp.ui.views.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.travelingapp.core.ui.R
import org.example.travelingapp.ui.theme.Dimens

/**
 * Editorial top bar. Transparent container, ink content, no shadow.
 * Title is preceded by an 8 dp ember accent square — the Meridian brand mark.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelToolBar(
    showBack: Boolean = false,
    @StringRes titleRes: Int = 0,
    navController: NavController,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(Dimens.spacingSm)
                        .background(MaterialTheme.colorScheme.secondary)
                )
                Spacer(Modifier.width(Dimens.spacingSm))
                Text(
                    text = stringResource(if (titleRes == 0) R.string.app_name else titleRes),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        actions = actions
    )
}
