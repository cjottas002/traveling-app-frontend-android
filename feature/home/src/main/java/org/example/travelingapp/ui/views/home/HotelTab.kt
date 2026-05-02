package org.example.travelingapp.ui.views.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import org.example.travelingapp.domain.entities.hotelmodel.Hotel
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelMonoFamily
import org.example.travelingapp.ui.views.components.TravelEditorialBlock
import org.example.travelingapp.ui.views.components.TravelLoader
import org.example.travelingapp.ui.views.components.TravelVerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.HotelViewModel

@Composable
fun HotelTab(hotelViewModel: HotelViewModel = hiltViewModel()) {
    val hotels by hotelViewModel.hotels.collectAsState(emptyList())
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.screenPadding)
    ) {
        item {
            TravelVerticalSpacer(Dimens.spacingMd)
            TravelEditorialBlock(
                kicker = stringResource(R.string.hotels_kicker),
                title = stringResource(R.string.hotels_title),
                accent = stringResource(R.string.hotels_title_accent)
            )
            TravelVerticalSpacer(Dimens.spacingLg)
        }

        items(hotels, key = { it.id }) { hotel ->
            HotelRow(hotel) {
                Toast.makeText(context, hotel.address.locality, Toast.LENGTH_SHORT).show()
            }
        }

        item { TravelVerticalSpacer(Dimens.screenBottomPadding) }
    }
}

/** Editorial hotel row: 96dp thumb · title · location mono · price mono right-aligned. */
@Composable
private fun HotelRow(hotel: Hotel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = Dimens.spacingMd),
        verticalAlignment = Alignment.Top
    ) {
        val context = LocalContext.current
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(hotel.optimizedThumbUrls.srpDesktop)
                .crossfade(300)
                .build(),
            contentDescription = hotel.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(Dimens.radiusXs))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            loading = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    TravelLoader()
                }
            }
        )
        Spacer(Modifier.size(Dimens.spacingMd))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = hotel.address.streetAddress,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(Dimens.spacingXxs))
            Text(
                text = hotel.address.locality.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(Dimens.spacingXs))
            Text(
                text = "★ ${hotel.guestReviews.rating}",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = TravelMonoFamily),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "€ ${hotel.ratePlan.price.current}",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = TravelMonoFamily),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.hotels_per_night).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
