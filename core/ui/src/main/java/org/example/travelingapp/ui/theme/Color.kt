package org.example.travelingapp.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * TravelingApp Brand Palette — "Meridian"
 *
 * Primary:    Ink     (#0B2A3A) — sea depth, calm authority, used for chrome
 * Secondary:  Ember   (#F26B4E) — coral, reserved for action and accent
 * Tertiary:   Citrine (#E5B342) — sun, pending sync, attention
 * Surface:    Bone    (#F4EFE7) — warm off-white, replaces clinical FAFDFD
 *
 * Hand-tuned, not Material Theme Builder output. Every value below has a job.
 */

// ── Light palette ──────────────────────────────────────────
val meridian_light_primary             = Color(0xFF0B2A3A) // Ink
val meridian_light_onPrimary           = Color(0xFFF4EFE7) // Bone on Ink
val meridian_light_primaryContainer    = Color(0xFFD3DFE6)
val meridian_light_onPrimaryContainer  = Color(0xFF0B2A3A)

val meridian_light_secondary           = Color(0xFFF26B4E) // Ember
val meridian_light_onSecondary         = Color(0xFFFFFFFF)
val meridian_light_secondaryContainer  = Color(0xFFFFE3DA) // Ember soft
val meridian_light_onSecondaryContainer = Color(0xFF591800)

val meridian_light_tertiary            = Color(0xFFE5B342) // Citrine — pending state
val meridian_light_onTertiary          = Color(0xFF3D2A00)
val meridian_light_tertiaryContainer   = Color(0xFFFAEAC1)
val meridian_light_onTertiaryContainer = Color(0xFF3D2A00)

val meridian_light_background          = Color(0xFFFBF8F2) // Paper
val meridian_light_onBackground        = Color(0xFF14181C) // Char
val meridian_light_surface             = Color(0xFFF4EFE7) // Bone
val meridian_light_onSurface           = Color(0xFF14181C)
val meridian_light_surfaceVariant      = Color(0xFFEBE3D5) // Bone 2
val meridian_light_onSurfaceVariant    = Color(0xFF3D464D) // Char 2
val meridian_light_outline             = Color(0xFF6B7680) // Char 3
val meridian_light_outlineVariant      = Color(0xFFDCD2BE) // Bone 3

val meridian_light_error               = Color(0xFFB23A2C) // Rust
val meridian_light_onError             = Color(0xFFFFFFFF)
val meridian_light_errorContainer      = Color(0xFFFFD9D2)
val meridian_light_onErrorContainer    = Color(0xFF410002)

val meridian_light_inverseSurface      = Color(0xFF0B2A3A)
val meridian_light_inverseOnSurface    = Color(0xFFF4EFE7)
val meridian_light_inversePrimary      = Color(0xFF8FCDB6)
val meridian_light_surfaceTint         = Color(0xFF0B2A3A)

// ── Dark palette ───────────────────────────────────────────
val meridian_dark_primary              = Color(0xFFA8D5E8) // Mist — washed ink
val meridian_dark_onPrimary            = Color(0xFF003547)
val meridian_dark_primaryContainer     = Color(0xFF14405A)
val meridian_dark_onPrimaryContainer   = Color(0xFFD3EAF5)

val meridian_dark_secondary            = Color(0xFFFF9275) // Ember light
val meridian_dark_onSecondary          = Color(0xFF591800)
val meridian_dark_secondaryContainer   = Color(0xFF7A2A14)
val meridian_dark_onSecondaryContainer = Color(0xFFFFE3DA)

val meridian_dark_tertiary             = Color(0xFFEDC76B)
val meridian_dark_onTertiary           = Color(0xFF3D2A00)
val meridian_dark_tertiaryContainer    = Color(0xFF5A3F00)
val meridian_dark_onTertiaryContainer  = Color(0xFFFAEAC1)

val meridian_dark_background           = Color(0xFF0E1518) // Coal
val meridian_dark_onBackground         = Color(0xFFE8E2D5)
val meridian_dark_surface              = Color(0xFF161E22) // Coal raised
val meridian_dark_onSurface            = Color(0xFFE8E2D5)
val meridian_dark_surfaceVariant       = Color(0xFF293338)
val meridian_dark_onSurfaceVariant     = Color(0xFFB8C2C9)
val meridian_dark_outline              = Color(0xFF7A858C)
val meridian_dark_outlineVariant       = Color(0xFF3A444A)

val meridian_dark_error                = Color(0xFFFFB4A8)
val meridian_dark_onError              = Color(0xFF690008)
val meridian_dark_errorContainer       = Color(0xFF8E1F12)
val meridian_dark_onErrorContainer     = Color(0xFFFFD9D2)

val meridian_dark_inverseSurface       = Color(0xFFE8E2D5)
val meridian_dark_inverseOnSurface     = Color(0xFF14181C)
val meridian_dark_inversePrimary       = Color(0xFF0B2A3A)
val meridian_dark_surfaceTint          = Color(0xFFA8D5E8)

// ── Brand extras (NOT in M3 ColorScheme — exposed via TravelColors) ──
val brand_ember_2          = Color(0xFFD9482C) // Pressed
val brand_moss             = Color(0xFF2F5D3E) // Success / synced
val brand_citrine          = Color(0xFFE5B342) // Pending
val brand_iris             = Color(0xFF6B4FB8) // Business role accent
val brand_iris_soft        = Color(0xFFE5DFF5)
val brand_ink_overlay_70   = Color(0xB30B2A3A) // 70% — for hero overlays
val brand_ink_overlay_40   = Color(0x660B2A3A) // 40%

/** State layer alphas — Material 3 spec exposed as named constants. */
object StateLayer {
    const val hover    = 0.08f
    const val focus    = 0.12f
    const val pressed  = 0.12f
    const val dragged  = 0.16f
    const val disabled = 0.38f
}
