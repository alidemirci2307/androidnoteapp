package com.demirci.note.ui.notelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.NoteState
import com.demirci.note.db.repo.NoteRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    
    lateinit var notes : LiveData<List<Note>>

    fun getList(uid : Long) = viewModelScope.launch {
        notes = noteRepository.getNotes(uid)
    }

    fun changeNoteState(note : Note) = viewModelScope.launch {
        when(note.state){
            NoteState.TODO -> note.state = NoteState.BEING_DONE
            NoteState.BEING_DONE -> note.state = NoteState.DONE
            NoteState.DONE -> note.state = NoteState.TODO
        }
        noteRepository.update(note)
    }

    fun delete(note : Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel() // ilgili yaşam döngüsü sona erdiğinde asenkron işlemleri durdurmak için yazdım
        Log.i("LoginViewModel", "LoginViewModel destroyed!")
    }


}

