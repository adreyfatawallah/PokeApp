package com.example.pokeapp.features.pokemon

import com.example.pokeapp.features.pokemon.data.datasource.PokemonLocalDataSource
import com.example.pokeapp.features.pokemon.data.datasource.PokemonLocalDataSourceImpl
import com.example.pokeapp.features.pokemon.data.datasource.PokemonRemoteDataSource
import com.example.pokeapp.features.pokemon.data.datasource.PokemonRemoteDataSourceImpl
import com.example.pokeapp.features.pokemon.data.repositories.PokemonRepositoryImpl
import com.example.pokeapp.features.pokemon.domain.repositories.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRemoteDataStore(
        impl: PokemonRemoteDataSourceImpl
    ) : PokemonRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindPokemonLocalDataStore(
        impl: PokemonLocalDataSourceImpl
    ) : PokemonLocalDataSource

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        impl: PokemonRepositoryImpl
    ) : PokemonRepository
}