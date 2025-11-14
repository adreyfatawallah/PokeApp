package com.example.pokeapp.features.auth.presentation.screen.login.components

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokeapp.R
import com.example.pokeapp.components.PasswordOutlinedTextField
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    login: () -> Unit,
    navigateToRegister: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
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
            label = stringResource(R.string.password),
            password = password,
            onPasswordChange = onPasswordChange
        )
        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            onClick = login
        ) {
            Text(text = stringResource(id = R.string.login))
        }
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = navigateToRegister
        ) {
            Text(text = stringResource(id = R.string.register))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    PokeAppTheme {
        LoginForm(
            username = "",
            onUsernameChange = { },
            password = "",
            onPasswordChange = { },
            login = { },
            navigateToRegister = { }
        )
    }
}