package org.example.travelingapp.ui.views.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.example.travelingapp.domain.entities.Transport
import org.example.travelingapp.feature.home.R
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.views.components.TravelCard
import org.example.travelingapp.ui.views.components.TravelCardStyle
import org.example.travelingapp.ui.views.components.TravelImage
import org.example.travelingapp.ui.views.components.TravelText
import org.example.travelingapp.ui.views.components.TravelVerticalSpacer
import org.example.travelingapp.ui.views.home.viewmodels.TransportViewModel

@Composable
fun TransportTab(transportViewModel: TransportViewModel = hiltViewModel()) {
    val transports by transportViewModel.transports.collectAsState(emptyList())
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.spacingMd)
    ) {
        item {
            TravelVerticalSpacer(Dimens.spacingMd)
            TravelText(
                text = stringResource(R.string.tab_transport),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            TravelVerticalSpacer(Dimens.spacingLg)
        }

        items(transports) { transport ->
            TransportItem(transport = transport) {
                Toast.makeText(context, transport.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun TransportItem(transport: Transport, onClick: () -> Unit) {
    TravelCard(
        modifier = Modifier
            .padding(bottom = Dimens.spacingSm)
            .clickable(onClick = onClick),
        style = TravelCardStyle.Elevated,
        contentPadding = Dimens.spacingMd
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                TravelText(
                    text = transport.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TravelVerticalSpacer(Dimens.spacingXs)
                TravelText(
                    text = transport.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            TravelImage(
                resId = transport.imageRes,
                contentDescription = null,
                modifier = Modifier
                    .height(Dimens.avatarMd)
                    .padding(start = Dimens.spacingSm)
            )
        }
    }
}
