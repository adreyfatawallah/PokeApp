package com.example.pokeapp.features.pokemon.presentation.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokeapp.R
import com.example.pokeapp.features.pokemon.domain.entities.list.Pokemon
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    key: String,
    onKeyChanged: (String) -> Unit,
    list: List<Pokemon>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = key,
            onValueChange = onKeyChanged,
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

@Preview(showBackground = true)
@Composable
private fun PokemonListPreview() {
    PokeAppTheme {
        PokemonList(key = "", onKeyChanged = { }, list = emptyList())
    }
}