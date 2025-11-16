package com.example.pokeapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun ProgressIndicator() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
        Text(text = "Loading...")
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressIndicatorPreview() {
    PokeAppTheme {
        ProgressIndicator()
    }
}