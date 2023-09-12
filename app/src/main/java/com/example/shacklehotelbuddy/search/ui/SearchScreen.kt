package com.example.shacklehotelbuddy.search.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.commoncore.todayInMillis
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun SearchScreen() {
    LocalContext.current

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderState() {
    var checkInDateText by rememberSaveable { mutableStateOf("") }
    var guestsText by rememberSaveable { mutableStateOf("1") }
    var childrenText by rememberSaveable { mutableStateOf("0") }
    val hintDate = stringResource(id = R.string.hint_date)
    var checkInDate by rememberSaveable { mutableStateOf(hintDate) }
    val calendarState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = todayInMillis()
    )
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    SearchContainer(
        guests = guestsText,
        onGuestsUpdate = { newInput -> guestsText = newInput },
        children = childrenText,
        onChildrenUpdate = { newInput -> childrenText = newInput },
        checkIn = checkInDate,
        onCheckInClick = { showDialog = true },
        onDialogDismiss = { showDialog = false },
        showDialog,
        calendarState,
        bottomSheetState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    bottomSheetState: SheetState,
    calendarState: DateRangePickerState,
    onDialogDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = bottomSheetState,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .background(Color.White)
            ) {
                SampleDatePickerView(calendarState)
                Button(
                    onClick = {
                        scope.launch {
                            bottomSheetState.hide()
                            onDialogDismiss.invoke()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp)
                ) {
                    Text("Let's go!", color = Color.White)
                }
            }
        },
        scrimColor = Color.Black.copy(alpha = 0.5f),
        onDismissRequest = {
            scope.launch {
                bottomSheetState.hide()
                onDialogDismiss.invoke()
            }
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleDatePickerView(state: DateRangePickerState) {
    DateRangePicker(
        state = state,
        dateValidator = dateValidator()

    )
}

fun dateValidator(): (Long) -> Boolean {
    return { timeInMillis ->
        timeInMillis >= Calendar.getInstance().timeInMillis
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchContainer(
    guests: String,
    onGuestsUpdate: (String) -> Unit,
    children: String,
    onChildrenUpdate: (String) -> Unit,
    checkIn: String,
    onCheckInClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    showDialog: Boolean,
    calendarState: DateRangePickerState,
    sheetState: SheetState
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
        modifier = Modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    ) {

        Column {
            TextInputSearchItem(
                icon = R.drawable.person,
                label = R.string.label_guests,
                hint = R.string.label_guests_hint,
                value = guests,
                onValueChange = onGuestsUpdate,
                inputType = KeyboardType.NumberPassword, //hmmm....
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            VerticalDivider()
            TextInputSearchItem(
                icon = R.drawable.supervisor_account,
                label = R.string.label_children,
                hint = R.string.label_children_hint,
                value = children,
                onValueChange = onChildrenUpdate,
                inputType = KeyboardType.NumberPassword,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Exit) }
                )
            )
            VerticalDivider()
            DateInputSearchItem(
                icon = R.drawable.event_available,
                label = R.string.label_check_in,
                value = checkIn,
                onItemClick = { onCheckInClick() }
            )
        }
    }
}


@Preview
@Composable
fun RenderStatePreview() {
    RenderState()
}