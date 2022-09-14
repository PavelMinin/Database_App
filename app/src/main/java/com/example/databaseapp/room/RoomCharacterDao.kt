package com.example.databaseapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomCharacterDao {
    @Query("SELECT * FROM roomcharacter")
    fun getAll(): List<RoomCharacter>

    @Query("SELECT * FROM roomcharacter WHERE name LIKE (:name)")
    fun loadByName(name: String): RoomCharacter

//    @Query("SELECT * FROM roomcharacter WHERE actor LIKE (:actor)")
//    fun loadByActor(actor: String): RoomCharacter

    @Insert
    fun insertAll(vararg characters: RoomCharacter)

    @Delete
    fun delete(character: RoomCharacter)
}