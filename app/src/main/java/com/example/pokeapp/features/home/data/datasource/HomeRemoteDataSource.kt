package com.example.pokeapp.features.home.data.datasource

import com.example.pokeapp.config.api.ApiService
import javax.inject.Inject

interface HomeRemoteDataSource {

}

class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRemoteDataSource {


}