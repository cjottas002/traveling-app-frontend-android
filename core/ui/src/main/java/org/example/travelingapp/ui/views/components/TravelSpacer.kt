package org.example.travelingapp.ui.views.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun TravelVerticalSpacer(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun TravelVerticalSpacer(modifier: Modifier = Modifier, height: Dp) {
    Spacer(modifier.height(height))
}

@Composable
fun TravelHorizontalSpacer(width: Dp) {
    Spacer(Modifier.width(width))
}