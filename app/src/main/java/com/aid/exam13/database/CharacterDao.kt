package com.aid.exam13.database

import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getById(id: Long): Character

    @Insert
    fun insert(employee: Character)

    @Update
    fun update(employee: Character)

    @Delete
    fun delete(employee: Character)
}