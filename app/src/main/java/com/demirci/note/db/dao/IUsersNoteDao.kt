package com.demirci.note.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.demirci.note.db.model.UserWithNotes

@Dao
interface IUsersNoteDao {
    @Transaction
    @Query("SELECT * from User")
    fun getUserWithNotes() : List<UserWithNotes>
}