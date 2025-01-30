package com.example.myhomework.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?= 0,
    @ColumnInfo("_firstName") val firstName: String,
    @ColumnInfo("_lastName") val lastName: String,
    @ColumnInfo("_email") val email: String,
    @ColumnInfo("_password") val password: String
)