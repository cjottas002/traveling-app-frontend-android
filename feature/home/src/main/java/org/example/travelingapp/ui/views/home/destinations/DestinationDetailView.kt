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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.components.AppTextField
import org.example.travelingapp.ui.views.components.AppToolBar
import org.example.travelingapp.ui.views.components.TravelPrimaryButton
import org.example.travelingapp.ui.views.components.TravelSecondaryButton
import org.example.travelingapp.ui.views.components.VerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.DestinationDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
    var expanded by remember { mutableStateOf(false) }

    viewModel.loadIfNeeded(destinationId)

    Scaffold(
        topBar = {
            AppToolBar(
                showBack = true,
                titleRes = if (isEditing) R.string.edit_destination else R.string.destination_detail,
                navController = navController
            ) {
                if (isAdmin && !isEditing) {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.delete_destination),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
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
                VerticalSpacer(Dimens.spacingMd)
            }

            if (isEditing && isAdmin) {
                // Edit mode
                AppTextField(value = name, onValueChange = { viewModel.onNameChanged(it) }, labelRes = R.string.destination_name)
                VerticalSpacer(Dimens.spacingSm)
                AppTextField(value = country, onValueChange = { viewModel.onCountryChanged(it) }, labelRes = R.string.destination_country)
                VerticalSpacer(Dimens.spacingSm)

                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                    AppTextField(
                        value = category,
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
                                text = { Text(option) },
                                onClick = { viewModel.onCategoryChanged(option); expanded = false }
                            )
                        }
                    }
                }

                VerticalSpacer(Dimens.spacingSm)
                AppTextField(value = description, onValueChange = { viewModel.onDescriptionChanged(it) }, labelRes = R.string.destination_description)
                VerticalSpacer(Dimens.spacingSm)
                AppTextField(value = imageUrl, onValueChange = { viewModel.onImageUrlChanged(it) }, labelRes = R.string.destination_image_url)
                VerticalSpacer(Dimens.spacingLg)

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
                Text(name, style = MaterialTheme.typography.headlineSmall)
                VerticalSpacer(Dimens.spacingXs)
                Text("$country · $category", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                VerticalSpacer(Dimens.spacingMd)
                if (description.isNotBlank()) {
                    Text(description, style = MaterialTheme.typography.bodyLarge)
                    VerticalSpacer(Dimens.spacingMd)
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
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.delete_destination)) },
            text = { Text(stringResource(R.string.delete_destination_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    viewModel.delete(
                        onSuccess = { navController.popBackStack() },
                        onError = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                    )
                }) { Text(stringResource(R.string.delete_destination)) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text(stringResource(R.string.cancel)) }
            }
        )
    }
}
