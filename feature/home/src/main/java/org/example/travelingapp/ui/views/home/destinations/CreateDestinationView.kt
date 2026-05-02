package org.example.travelingapp.ui.views.home.destinations

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.components.TravelDropdown
import org.example.travelingapp.ui.views.components.TravelPrimaryButton
import org.example.travelingapp.ui.views.components.TravelScaffold
import org.example.travelingapp.ui.views.components.TravelTextField
import org.example.travelingapp.ui.views.components.TravelToolBar
import org.example.travelingapp.ui.views.components.TravelVerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.CreateDestinationViewModel

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

    TravelScaffold(
        topBar = {
            TravelToolBar(
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
            TravelTextField(
                value = name,
                onValueChange = { viewModel.onNameChanged(it) },
                labelRes = R.string.destination_name,
                leadingIconRes = R.drawable.common_ic_castle
            )

            TravelVerticalSpacer(Dimens.spacingSm)

            TravelTextField(
                value = country,
                onValueChange = { viewModel.onCountryChanged(it) },
                labelRes = R.string.destination_country
            )

            TravelVerticalSpacer(Dimens.spacingSm)

            TravelDropdown(
                value = category,
                labelRes = R.string.destination_category,
                options = categories,
                onSelected = { viewModel.onCategoryChanged(it) }
            )

            TravelVerticalSpacer(Dimens.spacingSm)

            TravelTextField(
                value = description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                labelRes = R.string.destination_description
            )

            TravelVerticalSpacer(Dimens.spacingSm)

            TravelTextField(
                value = imageUrl,
                onValueChange = { viewModel.onImageUrlChanged(it) },
                labelRes = R.string.destination_image_url
            )

            TravelVerticalSpacer(Dimens.spacingLg)

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
