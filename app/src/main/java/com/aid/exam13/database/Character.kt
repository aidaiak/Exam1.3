package com.aid.exam13.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: Long,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val location: String
)