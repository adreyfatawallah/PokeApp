package com.example.pokeapp.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.pokeapp.R
import com.example.pokeapp.base.api.ApiError

@Composable
fun ErrorResponse(
    error: ApiError,
    modifier: Modifier = Modifier,
) {
    val message = when (error) {
        is ApiError.Response -> {
            error.message
        }
        is ApiError.JsonParse -> {
            error.message
        }
        is ApiError.ErrorReponse -> {
            error.error.toString()
        }
        is ApiError.Network -> {
            error.message
        }
        is ApiError.Unknown -> {
            error.message
        }
    }

    Text(
        text = message ?: stringResource(R.string.error_unknown),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}