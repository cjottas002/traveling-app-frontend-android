package org.example.travelingapp.domain.entities

/**
 * Onboarding slide data.
 *
 * @property image      drawable resource id for the hero photo
 * @property tag        small uppercase location chip shown at the top of the hero
 * @property step       short word describing the slide intent (e.g. "Descubre"),
 *                      rendered uppercase in the step indicator
 * @property title      first line(s) of the headline; may include "\n" for breaks
 * @property titleAccent optional final phrase rendered in italic ember as the
 *                      Meridian accent of the headline
 * @property desc       short paragraph below the headline
 */
data class PageData(
    val image: Int,
    val tag: String,
    val step: String,
    val title: String,
    val titleAccent: String?,
    val desc: String
)
