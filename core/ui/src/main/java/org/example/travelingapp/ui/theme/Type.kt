package org.example.travelingapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import org.example.travelingapp.core.ui.R

/** Provider for downloadable Google Fonts (GMS-served). Configured once in core/ui. */
val GoogleFontsProvider: GoogleFont.Provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage   = "com.google.android.gms",
    certificates      = R.array.com_google_android_gms_fonts_certs
)

private fun gFont(name: String) = GoogleFont(name)

/** Display family — Bricolage Grotesque (variable, opsz 12–96, italic). */
private val Display = FontFamily(
    Font(googleFont = gFont("Bricolage Grotesque"), fontProvider = GoogleFontsProvider, weight = FontWeight.Normal),
    Font(googleFont = gFont("Bricolage Grotesque"), fontProvider = GoogleFontsProvider, weight = FontWeight.Medium),
    Font(googleFont = gFont("Bricolage Grotesque"), fontProvider = GoogleFontsProvider, weight = FontWeight.SemiBold),
    Font(googleFont = gFont("Bricolage Grotesque"), fontProvider = GoogleFontsProvider, weight = FontWeight.Normal,
         style = FontStyle.Italic),
)

/** Body family — Inter (substituted for Geist, which isn't in Google Fonts). */
private val Body = FontFamily(
    Font(googleFont = gFont("Inter"), fontProvider = GoogleFontsProvider, weight = FontWeight.Light),
    Font(googleFont = gFont("Inter"), fontProvider = GoogleFontsProvider, weight = FontWeight.Normal),
    Font(googleFont = gFont("Inter"), fontProvider = GoogleFontsProvider, weight = FontWeight.Medium),
    Font(googleFont = gFont("Inter"), fontProvider = GoogleFontsProvider, weight = FontWeight.SemiBold),
)

/** Mono family — used as a meta-layer for prices, IDs, kickers, badges. */
private val Mono = FontFamily(
    Font(googleFont = gFont("JetBrains Mono"), fontProvider = GoogleFontsProvider, weight = FontWeight.Normal),
    Font(googleFont = gFont("JetBrains Mono"), fontProvider = GoogleFontsProvider, weight = FontWeight.Medium),
    Font(googleFont = gFont("JetBrains Mono"), fontProvider = GoogleFontsProvider, weight = FontWeight.SemiBold),
)

/** Exposed to wrappers that need mono explicitly (price tags, IDs, code). */
val TravelMonoFamily: FontFamily = Mono

/**
 * Material 3 typography scale, Meridian rendering.
 *
 * - Display + Headline + titleLarge use Bricolage (editorial voice).
 * - title{Medium,Small} + body* use Inter (neutral UI text).
 * - label* are deliberately **Mono + UPPERCASE + wide tracking** so they act as
 *   a recognisable meta-layer (kickers, badges, field labels). Apply
 *   `text-transform = uppercase` at the component level when rendering.
 */
val Typography = Typography(
    displayLarge   = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 57.sp, lineHeight = 60.sp, letterSpacing = (-1.4).sp),
    displayMedium  = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 45.sp, lineHeight = 50.sp, letterSpacing = (-1.0).sp),
    displaySmall   = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 36.sp, lineHeight = 42.sp, letterSpacing = (-0.5).sp),

    headlineLarge  = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 32.sp, lineHeight = 38.sp, letterSpacing = (-0.4).sp),
    headlineMedium = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 28.sp, lineHeight = 34.sp, letterSpacing = (-0.3).sp),
    headlineSmall  = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 24.sp, lineHeight = 30.sp, letterSpacing = (-0.2).sp),

    titleLarge     = TextStyle(fontFamily = Display, fontWeight = FontWeight.Medium,   fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp),
    titleMedium    = TextStyle(fontFamily = Body,    fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp, letterSpacing = 0.sp),
    titleSmall     = TextStyle(fontFamily = Body,    fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),

    bodyLarge      = TextStyle(fontFamily = Body,    fontWeight = FontWeight.Normal,   fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.2.sp),
    bodyMedium     = TextStyle(fontFamily = Body,    fontWeight = FontWeight.Normal,   fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.15.sp),
    bodySmall      = TextStyle(fontFamily = Body,    fontWeight = FontWeight.Normal,   fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.3.sp),

    labelLarge     = TextStyle(fontFamily = Mono,    fontWeight = FontWeight.Medium,   fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 1.4.sp),
    labelMedium    = TextStyle(fontFamily = Mono,    fontWeight = FontWeight.Medium,   fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 1.6.sp),
    labelSmall     = TextStyle(fontFamily = Mono,    fontWeight = FontWeight.Medium,   fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 1.8.sp),
)
