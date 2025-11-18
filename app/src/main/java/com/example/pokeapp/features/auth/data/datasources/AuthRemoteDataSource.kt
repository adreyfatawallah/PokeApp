package com.example.pokeapp.features.auth.data.datasources

import com.example.pokeapp.config.api.ApiService
import javax.inject.Inject

interface AuthRemoteDataSource {

}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRemoteDataSource {

}