package com.example.pokeapp.features.home.data.datasource

import com.example.pokeapp.core.sharedpref.SharedPrefModule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface HomeLocalDataSource {
    fun isLoggedIn(): Flow<Boolean>
}

@Singleton
class HomeLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPrefModule
) : HomeLocalDataSource {

    override fun isLoggedIn() = sharedPref.isLoggedIn()
}