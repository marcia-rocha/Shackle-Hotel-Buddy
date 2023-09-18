package com.example.shacklehotelbuddy.search

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.shacklehotelbuddy.search.model.SearchQuery
import com.example.shacklehotelbuddy.search.ui.search.SearchScreen
import com.example.shacklehotelbuddy.search.ui.search.SearchViewModel
import com.example.shacklehotelbuddy.search.ui.results.SearchResultsScreen
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShackleHotelBuddyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DestinationRoutes.SearchScreen.route
                ) {
                    composable(route = DestinationRoutes.SearchScreen.route) {
                        val viewModel = get<SearchViewModel>()
                        val recentSearches = viewModel.recentSearches.collectAsState(emptyList())
                        viewModel.getRecentSearches()

                        SearchScreen(
                            navController,
                            recentSearches = recentSearches.value,
                            onSearchClick = {
                                viewModel.updateRecentSearches(it)
                                navController.currentBackStackEntry?.savedStateHandle?.apply {
                                    set("searchQuery", it)
                                }
                                navController.navigate(DestinationRoutes.PropertyListScreen.route)
                            })
                    }

                    composable(
                        route = DestinationRoutes.PropertyListScreen.route
                    ) {
                        val viewModel = get<SearchViewModel>()
                        val searchQuery =
                            navController.previousBackStackEntry?.savedStateHandle?.get<SearchQuery>("searchQuery")
                        val propertiesResult = viewModel.properties.collectAsState()
                        val isLoading = viewModel.isLoading.collectAsState()

                        searchQuery?.let {
                            viewModel.findProperties(
                                adults = it.adults,
                                children = it.children,
                                checkInDate = it.checkInDateMillis,
                                checkOutDate = it.checkOutDateMillis
                            )
                        }

                        SearchResultsScreen(navController = navController, propertiesResult, isLoading)
                    }
                }
            }
        }
    }
}