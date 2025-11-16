package com.example.pokeapp.features.pokemon.presentation.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.base.api.onFailure
import com.example.pokeapp.base.api.onSuccess
import com.example.pokeapp.base.usecase.NoParam
import com.example.pokeapp.features.pokemon.domain.usecases.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getListPokemon: GetPokemonList,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val uiState = _uiState.onStart {
        fetchPokemonList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        PokemonListState.Loading
    )

    var key by mutableStateOf(value = "")
        private set

    fun onKeyChanged(key: String) {
        this.key = key
        Log.e("adrey", "onKeyChanged: ${this.key}")
    }

    private fun fetchPokemonList() {
        _uiState.value = PokemonListState.Loading

        viewModelScope.launch {
            getListPokemon(NoParam())
                .onSuccess {
                    _uiState.value = PokemonListState.Success(it)
                }
                .onFailure {
                    _uiState.value = PokemonListState.Error(it)
                }
        }
    }
}