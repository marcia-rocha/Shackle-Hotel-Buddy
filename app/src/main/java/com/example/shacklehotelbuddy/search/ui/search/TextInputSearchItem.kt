package com.example.shacklehotelbuddy.search.ui.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.search.ui.HorizontalDivider


@Composable
fun TextInputSearchItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    @StringRes hint: Int,
    value: String,
    onValueChange: (String) -> Unit,
    inputType: KeyboardType,
    keyboardActions: KeyboardActions
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchItemLabel(
            modifier = Modifier.weight(1f),
            icon = icon,
            label = label
        )

        HorizontalDivider()

        SearchItemLabelInput(
            modifier = Modifier.weight(1f),
            placeholder = hint,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = inputType
            ),
            keyboardActions = keyboardActions
        )
    }
}
