package com.example.pokeapp.features.pokemon.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokeapp.components.ErrorResponse
import com.example.pokeapp.components.ProgressIndicator
import com.example.pokeapp.features.pokemon.presentation.list.component.PokemonList

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val currentState = uiState.value) {
            is PokemonListState.Loading -> {
                ProgressIndicator()
            }
            is PokemonListState.Success -> {
                val listState = rememberLazyListState()

                val key = viewModel.key
                val list = currentState.list

                LaunchedEffect(listState) {
                    viewModel.checkScrollPosition(listState)
                }

                PokemonList(
                    key = key,
                    onKeyChanged = {
                        viewModel.onKeyChanged(it)
                    },
                    list = list,
                    listState = listState
                )
            }
            is PokemonListState.Error -> {
                ErrorResponse(
                    error = currentState.error,
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }
    }
}