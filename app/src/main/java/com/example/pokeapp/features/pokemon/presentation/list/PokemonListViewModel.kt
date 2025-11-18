package com.example.pokeapp.features.pokemon.presentation.list

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.base.api.onFailure
import com.example.pokeapp.base.api.onSuccess
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon
import com.example.pokeapp.features.pokemon.domain.entities.list.Pokemon
import com.example.pokeapp.features.pokemon.domain.usecases.GetPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemon: GetPokemon,
) : ViewModel() {

    private var nextUrl = "https://pokeapi.co/api/v2/pokemon?limit=30"

    private var isCanLoadNextPage by mutableStateOf(true)
    private var isLoadingNextPage by mutableStateOf(false)

    private val _uiState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val uiState = _uiState.asStateFlow()

    var key by mutableStateOf(value = "")
        private set

    init {
        fetchPokemon()
    }

    fun onKeyChanged(key: String) {
        this.key = key
        Log.e("adrey", "onKeyChanged: ${this.key}")
    }

    private fun fetchPokemon() {
        if (_uiState.value !is PokemonListState.Success) {
            _uiState.value = PokemonListState.Loading
        }

        if (!isCanLoadNextPage || isLoadingNextPage) return

        isLoadingNextPage = true

        Log.e("adrey", "fetchPokemon: $nextUrl")

        viewModelScope.launch {
            getPokemon(GetPokemon.Param(nextUrl))
                .onSuccess { newPokemon ->
                    val currentState = _uiState.value
                    if (currentState is PokemonListState.Success) {
                        val updatedList = currentState.list.toMutableList().apply {
                            addAll(newPokemon.pokemon)
                        }
                        _uiState.update {
                            PokemonListState.Success(updatedList)
                        }
                        isCanLoadNextPage = newPokemon.count > currentState.list.size
                    } else {
                        _uiState.value = PokemonListState.Success(newPokemon.pokemon)
                        isCanLoadNextPage = newPokemon.count > newPokemon.pokemon.size
                    }

                    newPokemon.next?.let {
                        nextUrl = it
                    }
                }
                .onFailure { error ->
                    if (_uiState.value !is PokemonListState.Success) {
                        _uiState.value = PokemonListState.Error(error)
                    }
                }
        }

        isLoadingNextPage = false
    }

    fun checkScrollPosition(listState: LazyListState) {
        viewModelScope.launch {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .filterNotNull()
                .collectLatest { lastVisibleIndex ->
                    val totalItems = listState.layoutInfo.totalItemsCount

                    if (totalItems > 0 && lastVisibleIndex >= totalItems - 5) {
                        fetchPokemon()
                    }
                }
        }
    }
}