package com.demirci.note.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {



    override fun onCleared() {
        super.onCleared()
        Log.i("SignUpViewModel", "SignUpViewModel destroyed!")
    }

}