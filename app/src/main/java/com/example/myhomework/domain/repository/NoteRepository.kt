package com.example.myhomework.domain.repository

import com.example.myhomework.data.model.NoteEntity

interface NoteRepository {

    suspend fun addNote(userId:Long, title:String, content:String)

    suspend fun getNotesByUser(userId:Long):List<NoteEntity>
}