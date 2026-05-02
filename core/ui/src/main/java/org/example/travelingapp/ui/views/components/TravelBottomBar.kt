package org.example.travelingapp.ui.views.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.example.travelingapp.ui.theme.Alpha
import org.example.travelingapp.ui.theme.Dimens

/**
 * Bottom navigation bar — Meridian style: 72 dp tall, hairline top divider,
 * active item marked with a 2 dp ember underline at the top edge (no pill).
 *
 * Custom row instead of `NavigationBar` to escape the M3 indicator pill.
 */
@Composable
fun TravelBottomBar(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.bottomNavHeight)
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Black.copy(alpha = Alpha.divider),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun RowScope.TravelBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    @StringRes labelRes: Int
) {
    val accent = MaterialTheme.colorScheme.secondary
    val activeColor = MaterialTheme.colorScheme.onBackground
    val mutedColor = MaterialTheme.colorScheme.onSurfaceVariant
    val contentColor = if (selected) activeColor else mutedColor

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(onClick = onClick)
            .drawBehind {
                if (selected) {
                    val strokeWidth = 2.dp.toPx()
                    drawLine(
                        color = accent,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = Dimens.spacingSm)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(labelRes),
                tint = contentColor,
                modifier = Modifier.padding(bottom = Dimens.spacingXxs)
            )
            Text(
                text = stringResource(labelRes),
                style = MaterialTheme.typography.labelSmall,
                color = contentColor
            )
        }
    }
}
