package org.example.travelingapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Brand-specific semantic colors that don't fit Material's 12-slot scheme:
 * sync states (synced/pending/offline), role accents (client/business),
 * pressed accent and hero image overlays.
 *
 * Consumed via [LocalTravelColors] inside any wrapper:
 *
 *   val colors = LocalTravelColors.current
 *   Box(modifier = Modifier.background(colors.synced)) { ... }
 */
@Immutable
data class TravelColors(
    val synced:        Color,
    val pending:       Color,
    val offline:       Color,
    val pressedAccent: Color,
    val roleClient:    Color,
    val roleBusiness:  Color,
    val heroOverlay:   Color,
)

val LightTravelColors = TravelColors(
    synced        = brand_moss,
    pending       = brand_citrine,
    offline       = Color(0xFF6B7680),
    pressedAccent = brand_ember_2,
    roleClient    = Color(0xFFF26B4E),
    roleBusiness  = brand_iris,
    heroOverlay   = brand_ink_overlay_70,
)

val DarkTravelColors = LightTravelColors.copy(
    synced      = Color(0xFF8FCDB6),
    pending     = Color(0xFFEDC76B),
    offline     = Color(0xFF7A858C),
    heroOverlay = Color(0xCC0E1518),
)

val LocalTravelColors = staticCompositionLocalOf { LightTravelColors }
