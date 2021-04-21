package com.demirci.note.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demirci.note.db.dao.UserDao
import com.demirci.note.db.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}