package com.demirci.note.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demirci.note.db.model.User
import com.demirci.note.db.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {


    lateinit var user : LiveData<User>
    lateinit var username : String
    lateinit var password : String

    fun insert(user : User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insert(user)
    }


    fun getUser(username : String, password : String)  {
        user = userRepository.getUser(username, password)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel() // ilgili yaşam döngüsü sona erdiğinde asenkron işlemleri durdurmak için yazdım
        Log.i("LoginViewModel", "LoginViewModel destroyed!")
    }


}

