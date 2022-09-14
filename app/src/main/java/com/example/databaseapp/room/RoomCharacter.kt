package com.example.databaseapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "actor")
    val actor: String


) {
    override fun toString(): String {
        return "ID: $id, name: $name, actor: $actor"
    }
}