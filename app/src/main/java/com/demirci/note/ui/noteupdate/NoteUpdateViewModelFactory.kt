package com.demirci.note.ui.noteupdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demirci.note.db.repo.NoteRepository


// ViewModelFactory desenini kullandım. Çünkü constructer kullanmam gerekiyordu.
class NoteUpdateViewModelFactory (private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteUpdateViewModel::class.java)) {
            return NoteUpdateViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}