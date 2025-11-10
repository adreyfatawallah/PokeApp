package com.example.pokeapp.features.home.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.features.home.domain.usescases.IsLoggedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    isLoggedIn: IsLoggedIn
) : ViewModel() {

    val uiState: StateFlow<Boolean> = isLoggedIn(null)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun isLogin() = viewModelScope.launch {
        uiState.first().let {
            Log.e("adrey", "isLogin: $it")
        }
    }
}