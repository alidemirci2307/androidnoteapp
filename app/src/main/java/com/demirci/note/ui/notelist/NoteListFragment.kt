package com.demirci.note.ui.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirci.note.NoteActivity
import com.demirci.note.NotesApplication
import com.demirci.note.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory(((requireActivity().application) as NotesApplication).noteRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
        binding.recyclerViewNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        //Log.e("uid", (requireActivity() as NoteActivity).user.uid.toString())
        viewModel.getList((requireActivity() as NoteActivity).user.uid)
        viewModel.notes.observe(viewLifecycleOwner, {

            binding.recyclerViewNotes.adapter = NoteListAdapter(requireContext(), it, viewModel, findNavController())
        })


    }

    private fun initEvents() {
        binding.floatingActionButtonNoteAdd.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteAddFragment()
            findNavController().navigate(action)
        }
    }
}