package com.demirci.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demirci.note.db.dao.INoteDao
import com.demirci.note.db.dao.IUserDao
import com.demirci.note.db.dao.IUsersNoteDao
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.User

@Database(entities = arrayOf(User::class, Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun IUserDao(): IUserDao
    abstract fun INoteDao(): INoteDao
    abstract fun IUsersNoteDao(): IUsersNoteDao

    companion object {
        // Birden fazla instance oluşturmamak için Singleton desenini kullandım.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Eğer instance null değilse aynı nesneyi döndürdüm
            // Null ise, ilgili nesneyi oluşturdum.
            return INSTANCE ?: synchronized(this) { // synchronized ile aynı anda nesneye erişim ihtimali olursa, sıralı bir şekilde erişim sağlanmasını gerçekleştirdim.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}