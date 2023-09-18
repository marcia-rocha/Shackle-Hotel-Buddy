package com.example.shacklehotelbuddy.search.ui.search


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.shacklehotelbuddy.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.commoncore.formatDate
import com.example.shacklehotelbuddy.commoncore.plus
import com.example.shacklehotelbuddy.commoncore.todayInMillis
import com.example.shacklehotelbuddy.search.model.SearchQuery
import com.example.shacklehotelbuddy.search.ui.VerticalDivider
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.Teal
import java.util.Date

@Composable
fun SearchScreen(
    navController: NavController,
    onSearchClick: (SearchQuery) -> Unit,
    recentSearches: List<SearchQuery>
) {
    LocalContext.current

    RenderState(
        navController = navController,
        onSearchClick = onSearchClick,
        recentSearches = recentSearches
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderState(
    navController: NavController,
    onSearchClick: (SearchQuery) -> Unit,
    recentSearches: List<SearchQuery>
) {
    //setting some default start times
    val checkInDefault = todayInMillis()
    val checkoutDefault = Date(checkInDefault).plus(2).time
    val calendarState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = checkInDefault,
        initialSelectedEndDateMillis = checkoutDefault
    )

    var guestsText by rememberSaveable { mutableStateOf("1") }
    var childrenText by rememberSaveable { mutableStateOf("0") }
    val checkIn = calendarState.selectedStartDateMillis?.let { formatDate(it) } ?: ""
    val checkOut = calendarState.selectedEndDateMillis?.let { formatDate(it) } ?: ""
    val selectedDate = "$checkIn - $checkOut"

    var dates by rememberSaveable { mutableStateOf(selectedDate) }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    SearchContainer(
        navController = navController,
        recentSearches = recentSearches,
        guests = guestsText,
        onGuestsUpdate = { newInput -> guestsText = newInput },
        children = childrenText,
        onChildrenUpdate = { newInput -> childrenText = newInput },
        dates = dates,
        onDatesClick = { showDialog = true },
        onDialogDismiss = {
            showDialog = false
            dates = calendarState.toFormattedSelectedDates()
        },
        showDialog,
        calendarState,
        bottomSheetState,
        onSearchClick = onSearchClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
private fun DateRangePickerState.toFormattedSelectedDates() : String {
    val checkIn = selectedStartDateMillis?.let { formatDate(it) } ?: ""
    val checkOut = selectedEndDateMillis?.let { formatDate(it) } ?: ""
    return "$checkIn - $checkOut"
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchContainer(
    navController: NavController,
    recentSearches: List<SearchQuery>,
    guests: String,
    onGuestsUpdate: (String) -> Unit,
    children: String,
    onChildrenUpdate: (String) -> Unit,
    dates: String,
    onDatesClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    showDialog: Boolean,
    calendarState: DateRangePickerState,
    sheetState: SheetState,
    onSearchClick: (SearchQuery) -> Unit
) {
    val focusManager = LocalFocusManager.current

    if (showDialog) {
        DateRangePickerDialog(
            bottomSheetState = sheetState,
            calendarState = calendarState,
            onDialogDismiss
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                text = stringResource(R.string.search_title),
                style = ShackleHotelBuddyTheme.typography.title,
                color = ShackleHotelBuddyTheme.colors.white
            )

            Spacer(modifier = Modifier.height(24.dp))

            //search box
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            ) {
                Column {
                    DateInputSearchItem(
                        icon = R.drawable.event_available,
                        label = R.string.label_dates,
                        value = dates,
                        onItemClick = { onDatesClick() }
                    )
                    VerticalDivider()
                    TextInputSearchItem(
                        icon = R.drawable.person,
                        label = R.string.label_guests,
                        hint = R.string.label_guests_hint,
                        value = guests,
                        onValueChange = onGuestsUpdate,
                        inputType = KeyboardType.Number,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                    )
                    VerticalDivider()
                    TextInputSearchItem(
                        icon = R.drawable.supervisor_account,
                        label = R.string.label_children,
                        hint = R.string.label_children_hint,
                        value = children,
                        onValueChange = onChildrenUpdate,
                        inputType = KeyboardType.Number,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Exit) }
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            //recent searches
            if (recentSearches.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    text = stringResource(R.string.recent_searches_title),
                    style = ShackleHotelBuddyTheme.typography.subtitle,
                    color = ShackleHotelBuddyTheme.colors.white
                )
            }

            LazyColumn {
                items(
                    items = recentSearches
                ) {
                    SearchQueryItem(
                        recentSearch = it,
                        navController = navController
                    )
                }
            }

            //button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f, true),
                verticalAlignment = Alignment.Bottom
            ) {
                Button(colors = ButtonDefaults.buttonColors(containerColor = Teal),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(60.dp)
                        .border(
                            width = 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    onClick = {
                        focusManager.clearFocus()

                        val searchQuery = SearchQuery(
                            checkInDateMillis = calendarState.selectedStartDateMillis ?: 0L,
                            checkOutDateMillis = calendarState.selectedEndDateMillis ?: 0L,
                            adults = guests.toIntOrNull() ?: 0,
                            children = children.toIntOrNull() ?: 0,
                            timestamp = System.currentTimeMillis()
                        )
                        onSearchClick(searchQuery)
                    }) {
                    Text(
                        text = stringResource(R.string.search_button),
                        style = ShackleHotelBuddyTheme.typography.button,
                        color = ShackleHotelBuddyTheme.colors.white
                    )
                }

            }
        }
    }
}


@Preview
@Composable
fun RenderStatePreview() {
    RenderState(
        onSearchClick = {},
        navController = rememberNavController(),
        recentSearches = emptyList()
    )
}