package com.example.myhomework.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("_userId") val userId: Long,
    @ColumnInfo("_title") val title: String,
    @ColumnInfo("_content") val content: String
)