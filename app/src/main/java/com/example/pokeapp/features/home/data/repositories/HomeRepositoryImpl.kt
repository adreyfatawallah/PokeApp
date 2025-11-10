package com.example.pokeapp.features.home.data.repositories

import com.example.pokeapp.features.home.data.datasource.HomeLocalDataSource
import com.example.pokeapp.features.home.domain.repositories.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {

    override fun isLoggedIn() = homeLocalDataSource.isLoggedIn()
}