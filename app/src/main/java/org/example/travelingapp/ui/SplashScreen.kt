package org.example.travelingapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.example.travelingapp.BuildConfig
import org.example.travelingapp.navigation.Route
import org.example.travelingapp.ui.theme.Alpha
import org.example.travelingapp.ui.theme.Dimens
import org.example.travelingapp.ui.theme.TravelMonoFamily
import org.example.travelingapp.ui.theme.TravelingAppTheme
import org.example.travelingapp.ui.views.components.BrandMarkSize
import org.example.travelingapp.ui.views.components.TravelBrandMark

// Splash is intentionally always dark — it's the brand reveal, not a themed surface.
private val SplashInk = Color(0xFF0B2A3A)
private val SplashBone = Color(0xFFF4EFE7)
private val SplashEmber = Color(0xFFF26B4E)

@Composable
fun SplashScreen(navController: NavController, store: Boolean) {
    val destination: Route = if (store) Route.Login else Route.OnBoarding

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(destination)
    }

    SplashContent()
}

/** Stateless splash UI extracted so previews can render without a navigator. */
@Composable
private fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashInk)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TravelBrandMark(
                size = BrandMarkSize.Large,
                color = SplashBone,
                accent = SplashEmber
            )
            Spacer(Modifier.height(Dimens.spacingMd))
            Text(
                text = "— SINCE 2026",
                style = MaterialTheme.typography.labelSmall,
                color = SplashBone.copy(alpha = Alpha.muted)
            )
        }

        // Version pinned to bottom centre
        Text(
            text = "v ${BuildConfig.VERSION_NAME} · build ${BuildConfig.VERSION_CODE}",
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = TravelMonoFamily
            ),
            color = SplashBone.copy(alpha = Alpha.divider * 3),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.spacingXl)
        )
    }
}

@Preview(showBackground = true, name = "Splash · Meridian")
@Composable
private fun SplashScreenPreview() {
    TravelingAppTheme {
        SplashContent()
    }
}
