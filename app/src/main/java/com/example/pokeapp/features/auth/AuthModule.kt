package com.example.pokeapp.features.auth

import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSource
import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSourceImpl
import com.example.pokeapp.features.auth.data.datasources.AuthRemoteDataSource
import com.example.pokeapp.features.auth.data.datasources.AuthRemoteDataSourceImpl
import com.example.pokeapp.features.auth.data.repositories.AuthRepositoryImpl
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        impl: AuthRemoteDataSourceImpl
    ) : AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAuthLocalDataSource(
        impl: AuthLocalDataSourceImpl
    ) : AuthLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ) : AuthRepository
}