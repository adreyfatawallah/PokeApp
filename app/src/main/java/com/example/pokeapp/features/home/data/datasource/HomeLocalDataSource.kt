package com.example.pokeapp.features.home.data.datasource

import com.example.pokeapp.config.sharedpref.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeLocalDataSource {
    fun isLoggedIn(): Flow<Boolean>
}

class HomeLocalDataSourceImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : HomeLocalDataSource {

    override fun isLoggedIn() = userPreferences.isLoggedIn()
}