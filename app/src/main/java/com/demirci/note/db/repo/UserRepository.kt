package com.demirci.note.db.repo

import androidx.lifecycle.LiveData
import com.demirci.note.db.dao.IUserDao
import com.demirci.note.db.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val IUserDao : IUserDao) {



    fun getUser(username : String, password : String) : LiveData<User> {
        return IUserDao.getUser(username, password)
    }

    suspend fun insert(user : User){
        IUserDao.insert(user)
    }

}