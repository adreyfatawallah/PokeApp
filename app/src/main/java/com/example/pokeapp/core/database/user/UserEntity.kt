package com.example.pokeapp.core.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val username: String,
    val password: String,
)