package com.example.pokeapp.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapp.R
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, Alignment.CenterVertically),
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Profile",
            fontWeight = FontWeight.W500,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.username),
                fontWeight = FontWeight.W300,
                fontSize = 20.sp,
            )
            Text(
                text = stringResource(R.string.username),
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.password),
                fontWeight = FontWeight.W300,
                fontSize = 20.sp,
            )
            Text(
                text = stringResource(R.string.password),
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            onClick = { }
        ) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    PokeAppTheme {
        ProfileScreen()
    }
}