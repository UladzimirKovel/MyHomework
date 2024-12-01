package com.example.myhomework.domain.model

sealed interface Auto {
    data class User(
        val brand: String,
        val status: String,
    ) : Auto

    data class Card(
        val releaseData: String
    ) : Auto
}