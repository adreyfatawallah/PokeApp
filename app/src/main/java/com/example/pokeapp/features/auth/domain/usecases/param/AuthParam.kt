package com.example.pokeapp.features.auth.domain.usecases.param

import com.example.pokeapp.core.database.user.UserEntity

data class AuthParam(
    val username: String,
    val password: String
)

fun AuthParam.toUserEntity(): UserEntity {
    return UserEntity(username = username, password = password)
}