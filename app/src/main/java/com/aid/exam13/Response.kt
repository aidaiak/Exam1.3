package com.aid.exam13

import com.google.gson.annotations.SerializedName

data class CharResult(
    @SerializedName("results")
    val items: List<Item>
)

data class Item(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val location: Location,
)

data class Location(
    val name: String,
    val url: String
)
