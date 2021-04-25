package com.demirci.note

import android.app.Application
import com.demirci.note.db.AppDatabase
import com.demirci.note.db.repo.NoteRepository
import com.demirci.note.db.repo.UserRepository

class NotesApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }  // ihtiyaç olduğunda oluşturulması için lazy kullandım
    val userRepository by lazy { UserRepository(database.IUserDao()) }
    val noteRepository by lazy { NoteRepository(database.INoteDao()) }
}