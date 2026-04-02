package org.example.travelingapp.ui.views.home.destinations

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.components.AppTextField
import org.example.travelingapp.ui.views.components.AppToolBar
import org.example.travelingapp.ui.views.components.TravelPrimaryButton
import org.example.travelingapp.ui.views.components.VerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.CreateDestinationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDestinationView(
    navController: NavController,
    viewModel: CreateDestinationViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val description by viewModel.description.collectAsState()
    val country by viewModel.country.collectAsState()
    val imageUrl by viewModel.imageUrl.collectAsState()
    val category by viewModel.category.collectAsState()
    val isEnabled by viewModel.isEnabled.collectAsState()
    val context = LocalContext.current

    val categories = listOf("beach", "mountain", "city", "nature")
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppToolBar(
                showBack = true,
                titleRes = R.string.create_destination,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(Dimens.spacingMd)
        ) {
            AppTextField(
                value = name,
                onValueChange = { viewModel.onNameChanged(it) },
                labelRes = R.string.destination_name,
                leadingIconRes = R.drawable.common_ic_castle
            )

            VerticalSpacer(Dimens.spacingSm)

            AppTextField(
                value = country,
                onValueChange = { viewModel.onCountryChanged(it) },
                labelRes = R.string.destination_country
            )

            VerticalSpacer(Dimens.spacingSm)

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                AppTextField(
                    value = category.ifEmpty { stringResource(R.string.destination_category) },
                    onValueChange = {},
                    labelRes = R.string.destination_category,
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    categories.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                            onClick = {
                                viewModel.onCategoryChanged(option)
                                expanded = false
                            }
                        )
                    }
                }
            }

            VerticalSpacer(Dimens.spacingSm)

            AppTextField(
                value = description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                labelRes = R.string.destination_description
            )

            VerticalSpacer(Dimens.spacingSm)

            AppTextField(
                value = imageUrl,
                onValueChange = { viewModel.onImageUrlChanged(it) },
                labelRes = R.string.destination_image_url
            )

            VerticalSpacer(Dimens.spacingLg)

            TravelPrimaryButton(
                textRes = R.string.create_destination,
                enabled = isEnabled,
                onClick = {
                    viewModel.create(
                        onSuccess = { navController.popBackStack() },
                        onError = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                    )
                }
            )
        }
    }
}
