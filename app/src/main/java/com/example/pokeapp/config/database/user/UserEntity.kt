package com.example.pokeapp.config.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.features.auth.domain.entities.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val username: String,
    val password: String,
)

fun UserEntity.toUser() : User {
    return User(id = id, username = username, password = password)
}