package com.example.pokeapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokeapp.core.database.user.UserDao
import com.example.pokeapp.core.database.user.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}