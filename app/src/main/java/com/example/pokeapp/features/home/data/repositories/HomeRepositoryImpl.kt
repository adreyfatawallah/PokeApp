package com.example.pokeapp.features.home.data.repositories

import com.example.pokeapp.features.home.data.datasource.HomeLocalDataSource
import com.example.pokeapp.features.home.data.datasource.HomeRemoteDataSource
import com.example.pokeapp.features.home.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource
) : HomeRepository {

    override fun isLoggedIn() : Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }
}