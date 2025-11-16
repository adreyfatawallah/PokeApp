package com.example.pokeapp.features.pokemon.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokeapp.R
import com.example.pokeapp.components.ErrorResponse
import com.example.pokeapp.components.ProgressIndicator
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    var name by rememberSaveable { mutableStateOf(value = "") }

    Box(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        when (val currentState = uiState.value) {
            is PokemonListState.Loading -> {
                ProgressIndicator()
            }
            is PokemonListState.Success -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                ) {
                    val list = currentState.pokemon.results

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null
                            )
                        },
                        placeholder = {
                            Text(text = stringResource(id = R.string.search_here))
                        }
                    )
                    LazyColumn {
                        items(list) {
                            Text(text = it.name, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
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

@Preview(showBackground = true)
@Composable
private fun PokemonListScreenPreview() {
    PokeAppTheme {
        PokemonListScreen()
    }
}