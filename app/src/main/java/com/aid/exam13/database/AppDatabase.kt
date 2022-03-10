package com.aid.exam13.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao(): CharacterDao

}