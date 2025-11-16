package com.example.pokeapp.features.auth.data.datasources

import com.example.pokeapp.core.api.ApiService
import javax.inject.Inject

interface AuthRemoteDataSource {

}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRemoteDataSource {

}