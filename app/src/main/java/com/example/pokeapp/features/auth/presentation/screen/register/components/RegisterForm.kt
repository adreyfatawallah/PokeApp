package com.example.pokeapp.features.auth.presentation.screen.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokeapp.R
import com.example.pokeapp.components.PasswordOutlinedTextField
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    register: () -> Unit
) {
    var retypePassword by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        OutlinedTextField(
            singleLine = true,
            value = username,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.username)
                )
            },
            onValueChange = onUsernameChange,
            label = { Text(stringResource(id = R.string.username)) },
        )
        PasswordOutlinedTextField(
            label = stringResource(id = R.string.password),
            password = password,
            onPasswordChange = onPasswordChange
        )
        PasswordOutlinedTextField(
            label = stringResource(id = R.string.retype_password),
            password = retypePassword,
            onPasswordChange = {
                retypePassword = it
            },
            isError = retypePassword.isNotBlank() && retypePassword != password
        )
        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            onClick = register,
            enabled = username.isNotBlank() && password.isNotBlank() && retypePassword == password
        ) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormPreview() {
    PokeAppTheme {
        RegisterForm(
            username = "",
            password = "",
            onUsernameChange = { },
            onPasswordChange = { },
            register = { }
        )
    }
}