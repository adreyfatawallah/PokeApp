package com.example.pokeapp.features.auth

import com.example.pokeapp.base.usecase.UseCase
import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSource
import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSourceImpl
import com.example.pokeapp.features.auth.data.repositories.UserRepositoryImpl
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.UserRepository
import com.example.pokeapp.features.auth.domain.usecases.AuthLogin
import com.example.pokeapp.features.auth.domain.usecases.AuthRegister
import com.example.pokeapp.features.auth.domain.usecases.LoginParam
import com.example.pokeapp.features.auth.domain.usecases.RegisterParam
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
    abstract fun provideAuthLocalDataStore(
        impl: AuthLocalDataSourceImpl
    ) : AuthLocalDataSource

    @Binds
    @Singleton
    abstract fun provideUserRepository(
        impl: UserRepositoryImpl
    ) : UserRepository

    @Binds
    @Singleton
    abstract fun provideAuthLoginUseCase(
        impl: AuthLogin
    ) : UseCase<User?, LoginParam>

    @Binds
    @Singleton
    abstract fun provideregisterUseCase(
        impl: AuthRegister
    ) : UseCase<Unit, RegisterParam>
}