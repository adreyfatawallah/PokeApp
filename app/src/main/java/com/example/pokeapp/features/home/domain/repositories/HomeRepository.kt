package com.example.pokeapp.features.home.domain.repositories

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun isLoggedIn(): Flow<Boolean>
}