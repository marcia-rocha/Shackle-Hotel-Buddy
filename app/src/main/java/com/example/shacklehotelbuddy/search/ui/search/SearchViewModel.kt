package com.example.shacklehotelbuddy.search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.data.repository.model.PropertiesResult
import com.example.shacklehotelbuddy.search.model.SearchQuery
import com.example.shacklehotelbuddy.search.usecase.GetPropertiesUseCase
import com.example.shacklehotelbuddy.search.usecase.GetRecentSearchesUseCase
import com.example.shacklehotelbuddy.search.usecase.UpdateRecentSearchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase,
    private val updateRecentSearchesUseCase: UpdateRecentSearchesUseCase,
    private val getPropertiesUseCase: GetPropertiesUseCase
) : ViewModel() {

    private var _properties = MutableStateFlow<PropertiesResult>(PropertiesResult.Success(emptyList()))
    val properties get() = _properties

    private val _recentSearches = MutableStateFlow(emptyList<SearchQuery>())
    val recentSearches get() = _recentSearches.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    fun findProperties(
        adults: Int,
        children: Int,
        checkInDate: Long,
        checkOutDate: Long,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            getPropertiesUseCase(
                checkInDate = checkInDate,
                checkOutDate = checkOutDate,
                adults = adults,
                children = children
            ).collect { result ->
                _properties.value = result
                _isLoading.value = false
            }
        }
    }

    fun getRecentSearches() {
        viewModelScope.launch {
            getRecentSearchesUseCase().collect {
                _recentSearches.value = it
            }
        }
    }

    fun updateRecentSearches(searchQuery: SearchQuery) {
        viewModelScope.launch {
            updateRecentSearchesUseCase(searchQuery).collect {
                _recentSearches.value = it
            }
        }
    }
}