package com.example.myhomework.data.api

import com.example.myhomework.data.model.ApiResponce
import com.example.myhomework.data.model.OneApiResponce
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiExample {

    @GET("/character")
    suspend fun getAllCharacters() : ApiResponce

    @GET("/character/{id}")
    suspend fun getOneCharacter(@Path(value = "id") id : Int) : OneApiResponce

    @GET("/character?name=Mickey%20Mouse")
    suspend fun getFilterCharacters() : ApiResponce

}