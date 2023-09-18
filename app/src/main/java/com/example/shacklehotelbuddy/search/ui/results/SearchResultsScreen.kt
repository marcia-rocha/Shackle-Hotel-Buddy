package com.example.shacklehotelbuddy.search.ui.results

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.data.repository.model.PropertiesResult
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchResultsScreen(
    navController: NavController,
    propertiesResult: State<PropertiesResult>,
    isLoading: State<Boolean>,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.search_results),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        style = ShackleHotelBuddyTheme.typography.subtitle
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                ResultsContainer(
                    resultState = propertiesResult,
                    isLoading = isLoading
                )
            }
        }
    )
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center),
            color = ShackleHotelBuddyTheme.colors.teal
        )
    }
}

@Composable
fun MessageView(
    @StringRes title: Int,
    @StringRes subtitle: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(title),
            style = ShackleHotelBuddyTheme.typography.subtitle,
            color = ShackleHotelBuddyTheme.colors.black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(subtitle),
            style = ShackleHotelBuddyTheme.typography.bodyMedium,
            color = ShackleHotelBuddyTheme.colors.black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ResultsContainer(
    resultState: State<PropertiesResult>,
    isLoading: State<Boolean>
) {
    if (!isLoading.value) {
        when (val result = resultState.value) {
            is PropertiesResult.Success -> {
                if (result.properties.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(
                            items = result.properties
                        ) {
                            PropertyItem(
                                property = it
                            )
                        }
                    }
                } else {
                    MessageView(
                        title = R.string.search__no_results_title,
                        subtitle = R.string.search_no_results_subtitle
                    )
                }
            }

            is PropertiesResult.Failure -> {
                MessageView(
                    title = R.string.search_generic_error_title,
                    subtitle = R.string.search_no_results_subtitle
                )
            }

            is PropertiesResult.NoInternet -> {
                MessageView(
                    title = R.string.search_no_internet_connection_title,
                    subtitle = R.string.search_no_internet_connection_subtitle
                )
            }
        }
    } else {
        LoadingView()
    }
}