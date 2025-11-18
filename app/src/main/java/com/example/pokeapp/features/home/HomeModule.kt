package com.example.pokeapp.features.home

import com.example.pokeapp.base.usecase.UseCase
import com.example.pokeapp.features.home.data.datasource.HomeLocalDataSource
import com.example.pokeapp.features.home.data.datasource.HomeLocalDataSourceImpl
import com.example.pokeapp.features.home.data.datasource.HomeRemoteDataSource
import com.example.pokeapp.features.home.data.datasource.HomeRemoteDataSourceImpl
import com.example.pokeapp.features.home.data.repositories.HomeRepositoryImpl
import com.example.pokeapp.features.home.domain.repositories.HomeRepository
import com.example.pokeapp.features.home.domain.usescases.IsLoggedIn
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataStore(
        impl: HomeRemoteDataSourceImpl
    ) : HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindHomeLocalDataStore(
        impl: HomeLocalDataSourceImpl
    ) : HomeLocalDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ) : HomeRepository

    @Binds
    @Singleton
    abstract fun bindIsLoggedIn(
        impl: IsLoggedIn
    ) : UseCase<Flow<Boolean>, Any?>
}