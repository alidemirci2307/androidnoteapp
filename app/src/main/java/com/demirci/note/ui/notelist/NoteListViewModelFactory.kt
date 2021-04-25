package com.demirci.note.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demirci.note.db.repo.NoteRepository


// ViewModelFactory desenini kullandım. Çünkü constructer kullanmam gerekiyordu.
class NoteListViewModelFactory (private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            return NoteListViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}