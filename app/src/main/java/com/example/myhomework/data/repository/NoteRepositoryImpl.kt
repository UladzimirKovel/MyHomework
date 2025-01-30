package com.example.myhomework.data.repository

import com.example.myhomework.data.database.NoteDao
import com.example.myhomework.data.model.NoteEntity
import com.example.myhomework.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao): NoteRepository  {

    override suspend fun getNotesByUser(userId: Long): List<NoteEntity> {
        return noteDao.getNotesByUser(userId)
    }

    override suspend fun addNote(userId: Long, title: String, content: String) {
        val note = NoteEntity(userId = userId, title = title, content = content)
        noteDao.create(note)
    }
}