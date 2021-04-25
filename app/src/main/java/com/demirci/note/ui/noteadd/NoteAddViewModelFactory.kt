package com.demirci.note.ui.noteadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demirci.note.db.repo.NoteRepository


// ViewModelFactory desenini kullandım. Çünkü constructer kullanmam gerekiyordu.
class NoteAddViewModelFactory (private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteAddViewModel::class.java)) {
            return NoteAddViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}