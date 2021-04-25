package com.demirci.note.db.repo

import androidx.lifecycle.LiveData
import com.demirci.note.db.dao.INoteDao
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.UserWithNotes

class NoteRepository(private val INoteDao : INoteDao) {



    fun getNotes(uid : Long) : LiveData<List<Note>> {
        return INoteDao.getAll(uid)
    }

    fun getUserWithNotes() : LiveData<List<UserWithNotes>> {
        return INoteDao.getUserWithNotes()
    }

    suspend fun insert(note : Note){
        INoteDao.insert(note)
    }

    suspend fun update(note : Note){
        INoteDao.update(note)
    }

    suspend fun delete(note : Note){
        INoteDao.delete(note)
    }

}