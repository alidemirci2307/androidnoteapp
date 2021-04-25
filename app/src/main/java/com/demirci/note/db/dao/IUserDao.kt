package com.demirci.note.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demirci.note.db.model.User
import com.demirci.note.db.model.UserWithNotes

@Dao
interface IUserDao {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE username = :username and password = :password LIMIT 1")
    fun getUser(username: String, password: String): LiveData<User>

    @Transaction
    @Query("SELECT * from User")
    fun getUserWithNotes() : List<UserWithNotes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

}