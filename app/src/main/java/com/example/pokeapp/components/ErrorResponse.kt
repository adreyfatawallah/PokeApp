package com.example.pokeapp.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.pokeapp.base.api.ApiError

@Composable
fun ErrorResponse(
    error: ApiError,
    modifier: Modifier = Modifier,
) {
    when (error) {
        is ApiError.Response -> {
            error.message?.let {
                Text(text = it, textAlign = TextAlign.Center, modifier = modifier)
            }
        }

        is ApiError.JsonParse -> {
            error.message?.let {
                Text(text = it, textAlign = TextAlign.Center, modifier = modifier)
            }
        }

        is ApiError.ErrorReponse -> {
            Text(text = error.error.toString(), textAlign = TextAlign.Center, modifier = modifier)
        }

        is ApiError.Network -> {
            error.message?.let {
                Text(text = it, textAlign = TextAlign.Center, modifier = modifier)
            }
        }

        is ApiError.Unknown -> {
            error.message?.let {
                Text(text = it, textAlign = TextAlign.Center, modifier = modifier)
            }
        }
    }
}