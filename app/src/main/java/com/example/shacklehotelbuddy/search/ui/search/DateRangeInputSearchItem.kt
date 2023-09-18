package com.example.shacklehotelbuddy.search.ui.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.search.ui.HorizontalDivider

@Composable
fun DateInputSearchItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    value: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchItemLabel(
            modifier = Modifier
                .weight(1f),
            icon = icon,
            label = label
        )
        HorizontalDivider()
        ClickableText(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            text = AnnotatedString(value),
            onClick = { onItemClick.invoke() }
        )
    }
}