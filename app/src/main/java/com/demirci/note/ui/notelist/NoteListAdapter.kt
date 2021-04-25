package com.demirci.note.ui.notelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.demirci.note.common.DateUtils
import com.demirci.note.databinding.ItemNoteBinding
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.NoteState

class NoteListAdapter(val context : Context, val list : List<Note>, val noteListViewModel: NoteListViewModel, val navController: NavController) :  RecyclerView.Adapter<NoteListAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewNoteTitle.text = list[position].noteTitle
        holder.binding.textViewNoteDescription.text = list[position].noteDescription
        holder.binding.textViewState.text = NoteState.getState(list[position].state!!)
        holder.binding.materialCardViewItem.setCardBackgroundColor(ContextCompat.getColor(context,NoteState.getColor(list[position].state!!)))
        holder.binding.textViewNoteDate.text = DateUtils.convertLongToTime(list[position].updated_date)

        holder.binding.imageViewNoteStateChanger.setOnClickListener {
            noteListViewModel.changeNoteState(list[position])
        }

        holder.binding.imageViewNoteDelete.setOnClickListener {
            noteListViewModel.delete(list[position])
        }

        holder.binding.materialCardViewItem.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteUpdateFragment(list[position])
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}