package org.example.travelingapp.ui.views.home.destinations

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.components.TravelDialog
import org.example.travelingapp.ui.views.components.TravelDropdown
import org.example.travelingapp.ui.views.components.TravelIconButton
import org.example.travelingapp.ui.views.components.TravelPrimaryButton
import org.example.travelingapp.ui.views.components.TravelScaffold
import org.example.travelingapp.ui.views.components.TravelSecondaryButton
import org.example.travelingapp.ui.views.components.TravelText
import org.example.travelingapp.ui.views.components.TravelTextField
import org.example.travelingapp.ui.views.components.TravelToolBar
import org.example.travelingapp.ui.views.components.TravelVerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.DestinationDetailViewModel

@Composable
fun DestinationDetailView(
    destinationId: String,
    navController: NavController,
    isAdmin: Boolean = false,
    viewModel: DestinationDetailViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val description by viewModel.description.collectAsState()
    val country by viewModel.country.collectAsState()
    val imageUrl by viewModel.imageUrl.collectAsState()
    val category by viewModel.category.collectAsState()
    val isEditing by viewModel.isEditing.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current
    var showDeleteDialog by remember { mutableStateOf(false) }

    val categories = listOf("beach", "mountain", "city", "nature")

    viewModel.loadIfNeeded(destinationId)

    TravelScaffold(
        topBar = {
            TravelToolBar(
                showBack = true,
                titleRes = if (isEditing) R.string.edit_destination else R.string.destination_detail,
                navController = navController
            ) {
                if (isAdmin && !isEditing) {
                    TravelIconButton(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        iconTint = MaterialTheme.colorScheme.onPrimary,
                        onClick = { showDeleteDialog = true }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(Dimens.spacingMd)
        ) {
            // Image
            if (imageUrl.isNotBlank()) {
                val imgModifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.cardImageHeight)

                if (imageUrl.startsWith("local:")) {
                    val resName = imageUrl.removePrefix("local:")
                    val resId = context.resources.getIdentifier(resName, "drawable", context.packageName)
                    if (resId != 0) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = name,
                            modifier = imgModifier,
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = name,
                        modifier = imgModifier,
                        contentScale = ContentScale.Crop
                    )
                }
                TravelVerticalSpacer(Dimens.spacingMd)
            }

            if (isEditing && isAdmin) {
                // Edit mode
                TravelTextField(value = name, onValueChange = { viewModel.onNameChanged(it) }, labelRes = R.string.destination_name)
                TravelVerticalSpacer(Dimens.spacingSm)
                TravelTextField(value = country, onValueChange = { viewModel.onCountryChanged(it) }, labelRes = R.string.destination_country)
                TravelVerticalSpacer(Dimens.spacingSm)

                TravelDropdown(
                    value = category,
                    labelRes = R.string.destination_category,
                    options = categories,
                    onSelected = { viewModel.onCategoryChanged(it) }
                )

                TravelVerticalSpacer(Dimens.spacingSm)
                TravelTextField(value = description, onValueChange = { viewModel.onDescriptionChanged(it) }, labelRes = R.string.destination_description)
                TravelVerticalSpacer(Dimens.spacingSm)
                TravelTextField(value = imageUrl, onValueChange = { viewModel.onImageUrlChanged(it) }, labelRes = R.string.destination_image_url)
                TravelVerticalSpacer(Dimens.spacingLg)

                Row(modifier = Modifier.fillMaxWidth()) {
                    TravelSecondaryButton(
                        textRes = R.string.cancel,
                        onClick = { viewModel.cancelEdit() },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.padding(Dimens.spacingXs))
                    TravelPrimaryButton(
                        textRes = R.string.save,
                        onClick = {
                            viewModel.save(
                                onSuccess = { navController.popBackStack() },
                                onError = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                // View mode
                TravelText(text = name, style = MaterialTheme.typography.headlineSmall)
                TravelVerticalSpacer(Dimens.spacingXs)
                TravelText(
                    text = "$country · $category",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TravelVerticalSpacer(Dimens.spacingMd)
                if (description.isNotBlank()) {
                    TravelText(text = description, style = MaterialTheme.typography.bodyLarge)
                    TravelVerticalSpacer(Dimens.spacingMd)
                }

                if (isAdmin) {
                    TravelPrimaryButton(
                        textRes = R.string.edit_destination,
                        onClick = { viewModel.startEdit() }
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        TravelDialog(
            onDismissRequest = { showDeleteDialog = false },
            titleRes = R.string.delete_destination,
            textRes = R.string.delete_destination_confirm,
            confirmTextRes = R.string.delete_destination,
            onConfirm = {
                showDeleteDialog = false
                viewModel.delete(
                    onSuccess = { navController.popBackStack() },
                    onError = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                )
            },
            dismissTextRes = R.string.cancel,
            onDismiss = { showDeleteDialog = false }
        )
    }
}
