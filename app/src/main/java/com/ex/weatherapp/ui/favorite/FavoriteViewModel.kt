package com.ex.weatherapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ex.weatherapp.data.models.City
import com.ex.weatherapp.data.repositories.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class  FavoriteUiState(
    val isShowLoad: Boolean = false,
    val list: List<City> = emptyList()
)

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        getAllFavorite()
    }

    private fun getAllFavorite() {
        viewModelScope.launch {
            cityRepository.getAllCity()
                .collect { cities ->
                    _uiState.value = uiState.value.copy(isShowLoad = false, list = cities)
                }
        }
    }
}