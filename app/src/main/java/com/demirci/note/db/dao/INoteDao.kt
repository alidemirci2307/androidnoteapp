package com.demirci.note.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.User
import com.demirci.note.db.model.UserWithNotes

@Dao
interface INoteDao {
    @Query("SELECT * FROM Note WHERE note_user_id = :uid ORDER BY updated_date desc")
    fun getAll(uid : Long): LiveData<List<Note>> // zaten asenkron çalıştığı için suspend tanımlamadım

    @Transaction
    @Query("SELECT * from User")
    fun getUserWithNotes() : LiveData<List<UserWithNotes>>


    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}