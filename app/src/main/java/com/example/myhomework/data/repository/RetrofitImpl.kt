package com.example.myhomework.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl{
    
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
