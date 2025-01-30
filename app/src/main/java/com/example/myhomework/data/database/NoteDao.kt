package com.example.myhomework.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myhomework.data.model.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(note:NoteEntity)

    @Query("SELECT * FROM notes WHERE _userId = :userId")
    fun getNotesByUser(userId:Long): List<NoteEntity>
}