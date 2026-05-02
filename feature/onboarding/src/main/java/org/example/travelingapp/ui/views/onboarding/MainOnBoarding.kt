package org.example.travelingapp.ui.views.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.example.travelingapp.core.datastore.StoreBoarding
import org.example.travelingapp.domain.entities.PageData
import org.example.travelingapp.feature.onboarding.R

@Composable
fun MainOnBoarding(store: StoreBoarding, onNavigateToLogin: () -> Unit) {

    val items = listOf(
        PageData(
            image = R.drawable.onboarding1_image,
            tag = stringResource(R.string.onboarding1_tag),
            step = stringResource(R.string.onboarding1_step),
            title = stringResource(R.string.onboarding1_title),
            titleAccent = stringResource(R.string.onboarding1_title_accent),
            desc = stringResource(R.string.onboarding1_main_text)
        ),
        PageData(
            image = R.drawable.onboarding2_image,
            tag = stringResource(R.string.onboarding2_tag),
            step = stringResource(R.string.onboarding2_step),
            title = stringResource(R.string.onboarding2_title),
            titleAccent = stringResource(R.string.onboarding2_title_accent),
            desc = stringResource(R.string.onboarding2_main_text)
        ),
        PageData(
            image = R.drawable.onboarding3_image,
            tag = stringResource(R.string.onboarding3_tag),
            step = stringResource(R.string.onboarding3_step),
            title = stringResource(R.string.onboarding3_title),
            titleAccent = stringResource(R.string.onboarding3_title_accent),
            desc = stringResource(R.string.onboarding3_main_text)
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { items.size }
    )

    OnBoardingPager(
        pages = items,
        pagerState = pagerState,
        store = store,
        onNavigateToLogin = onNavigateToLogin,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}
