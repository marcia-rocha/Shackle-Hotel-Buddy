package com.example.shacklehotelbuddy.search.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@Composable
fun SearchItemLabel(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    @StringRes accessibilityLabel: Int? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = accessibilityLabel?.run { stringResource(id = this) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(id = label))
    }
}

@Preview
@Composable
fun SearchItemLabelPreview() {
    ShackleHotelBuddyTheme {
        SearchItemLabel(
            icon = R.drawable.event_upcoming,
            label = R.string.app_name,
            accessibilityLabel = null
        )
    }
}
