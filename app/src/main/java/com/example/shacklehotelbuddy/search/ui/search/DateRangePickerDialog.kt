package com.example.shacklehotelbuddy.search.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.Calendar

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                DatePickerView(calendarState)
                Spacer(modifier = Modifier.height(16.dp))

                // Separate Column for the Button. This is commented because I didn't make it work in time.
                // users can only dismiss the dialog and that's the way to pick the dates, which is not ideal
                /*Row(
                    modifier = Modifier.
                    weight(0.15f, true)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
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
                            .padding(end = 16.dp)
                    ) {
                        Text("Let's go!", color = Color.White)
                    }

                    Spacer(Modifier.height(32.dp))
                }*/
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
fun DatePickerView(state: DateRangePickerState) {
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