package com.example.pokeapp.core.database.user

import com.example.pokeapp.features.auth.domain.entities.User

fun UserEntity.toDomain() : User {
    return User(id = id, username = username, password = password)
}

fun User.toEntity() : UserEntity {
    return UserEntity(id = id, username = username, password = password)
}