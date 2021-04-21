package com.demirci.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demirci.note.db.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }


    private fun init() {


    }
}