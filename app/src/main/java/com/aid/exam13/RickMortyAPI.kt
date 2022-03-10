package com.aid.exam13

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyAPI {

    @GET("character")
    fun getCharacters(): Observable<CharResult>

    @GET("character/{id}")
    fun getCharacters(@Path("id") id: Long): Observable<Item>

}