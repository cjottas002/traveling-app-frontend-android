package org.example.travelingapp.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design tokens for spacing, sizing, elevation, corner radius, motion,
 * shadows, gradients and opacities.
 *
 * Usage: `Dimens.spacingMd` instead of raw `16.dp` literals.
 */
object Dimens {

    // ── Spacing ────────────────────────────────────────────
    val spacingXxs: Dp  = 2.dp
    val spacingXs:  Dp  = 4.dp
    val spacingSm:  Dp  = 8.dp
    val spacingMd:  Dp  = 16.dp
    val spacingLg:  Dp  = 24.dp
    val spacingXl:  Dp  = 32.dp
    val spacingXxl: Dp  = 48.dp
    val spacingHuge: Dp = 64.dp   // Hero negative-space (Onboarding/Login)

    // ── Icon sizes ─────────────────────────────────────────
    val iconSm: Dp = 16.dp
    val iconMd: Dp = 20.dp        // Line icons stroke fino
    val iconLg: Dp = 32.dp
    val iconXl: Dp = 48.dp

    // ── Component heights (touch targets ≥ 48 dp per Material) ─
    val buttonHeight:    Dp = 52.dp   // Editorial presence
    val textFieldHeight: Dp = 56.dp
    val toolbarHeight:   Dp = 64.dp
    val bottomNavHeight: Dp = 72.dp   // Mono labels más compactas

    // ── Card / image ───────────────────────────────────────
    val cardImageHeight: Dp = 200.dp  // Hero más editorial
    val avatarSm: Dp = 36.dp
    val avatarMd: Dp = 56.dp
    val avatarLg: Dp = 88.dp

    // ── Corner radius ──────────────────────────────────────
    val radiusXs:   Dp = 4.dp     // Buttons, inputs (editorial, no pastilla)
    val radiusSm:   Dp = 8.dp
    val radiusMd:   Dp = 12.dp
    val radiusLg:   Dp = 16.dp
    val radiusXl:   Dp = 24.dp
    val radiusFull: Dp = 100.dp

    // ── Elevation ──────────────────────────────────────────
    val elevationNone: Dp = 0.dp
    val elevationSm:   Dp = 1.dp
    val elevationMd:   Dp = 3.dp
    val elevationLg:   Dp = 6.dp

    // ── Layout tokens ──────────────────────────────────────
    val screenPadding:       Dp = 24.dp
    val screenBottomPadding: Dp = 24.dp
    val cardPadding:         Dp = 20.dp
    val cardSpacing:         Dp = 16.dp
    val sectionSpacing:      Dp = 32.dp
}

/** Animation durations in milliseconds. */
object Motion {
    const val short:        Int = 120
    const val medium:       Int = 220
    const val long:         Int = 400
    const val entranceLong: Int = 600
}

/** Cubic-bezier easings used across the system. */
object Easings {
    val standard:   Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)   // Material 3 emphasised
    val decelerate: Easing = CubicBezierEasing(0f,   0f, 0f, 1f)
    val accelerate: Easing = CubicBezierEasing(0.3f, 0f, 1f, 1f)
}

/**
 * Shadow elevations. Use with `Modifier.shadow(elevation, ambientColor = Shadow.tint, spotColor = Shadow.tint)`
 * to keep shadows tinted with brand ink instead of pure black.
 */
object Shadow {
    val flat:  Dp = 0.dp
    val lift:  Dp = 2.dp
    val float: Dp = 8.dp
    val modal: Dp = 24.dp
    val tint: Color = Color(0xFF0B2A3A)
}

/** Semantic opacity tokens. */
object Alpha {
    const val disabled = 0.38f
    const val muted    = 0.65f
    const val subtle   = 0.85f
    const val scrim    = 0.55f
    const val divider  = 0.10f
}

/** Blur radii. Compose 12+ supports `Modifier.blur()`. */
object Blur {
    val glass: Dp = 12.dp
}

/** Pre-built brand gradients used across hero areas, FABs, banners. */
object TravelGradients {
    /** Vertical scrim used over hero photos so text survives any image. */
    val heroOverlay: Brush = Brush.verticalGradient(
        0.0f to Color.Transparent,
        0.6f to Color(0x660B2A3A),
        1.0f to Color(0xF20B2A3A),
    )

    /** Linear ember gradient for primary CTAs and promotional banners. */
    val emberCTA: Brush = Brush.linearGradient(
        listOf(Color(0xFFF26B4E), Color(0xFFD9482C))
    )

    /** Linear ink gradient for hero cards and featured sections. */
    val deepDive: Brush = Brush.linearGradient(
        listOf(Color(0xFF1D5A7A), Color(0xFF0B2A3A))
    )

    /** Vertical fade-up to bone — scrim over long lists. */
    val boneFadeUp: Brush = Brush.verticalGradient(
        0.0f to Color.Transparent,
        1.0f to Color(0xFFF4EFE7),
    )
}
