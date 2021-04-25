package com.demirci.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demirci.note.db.model.User

class NoteActivity : AppCompatActivity() {
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        user = intent.extras?.getSerializable("user") as User
    }


    override fun onBackPressed() {
        // geri tuşunu iptal ettim

    }
}