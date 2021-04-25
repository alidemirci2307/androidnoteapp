package com.demirci.note.ui.noteupdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demirci.note.db.model.Note
import com.demirci.note.db.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class NoteUpdateViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    
    lateinit var note : Note
    lateinit var title: String
    lateinit var description: String
    var imagePath: String = ""

    fun insert(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun update(note : Note) = viewModelScope.launch {
        noteRepository.update(note)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel() // ilgili yaşam döngüsü sona erdiğinde asenkron işlemleri durdurmak için yazdım
    }


}

