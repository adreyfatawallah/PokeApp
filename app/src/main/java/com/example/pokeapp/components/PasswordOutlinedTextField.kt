package com.example.pokeapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokeapp.R
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun PasswordOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    isError: Boolean = false,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible)
        VisualTransformation.None
    else
        PasswordVisualTransformation()

    val iconImage = if (passwordVisible)
        Icons.Default.Visibility
    else
        Icons.Default.VisibilityOff

    val description = if (passwordVisible)
        stringResource(id = R.string.hide_password)
    else
        stringResource(id = R.string.show_password)

    OutlinedTextField(
        singleLine = true,
        value = password,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(id = R.string.password)
            )
        },
        onValueChange = onPasswordChange,
        label = { Text(label) },
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    imageVector = iconImage,
                    contentDescription = description
                )
            }
        },
        isError = isError,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordOutlinedTextFieldPreview() {
    PokeAppTheme {
        PasswordOutlinedTextField(
            label = stringResource(R.string.password),
            password = "",
            onPasswordChange = { }
        )
    }
}