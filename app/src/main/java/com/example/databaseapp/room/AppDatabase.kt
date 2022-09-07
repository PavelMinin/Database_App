package com.example.databaseapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomCharacter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): RoomCharacterDao
}