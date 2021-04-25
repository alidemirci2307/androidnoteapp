package com.demirci.note.ui.noteadd

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demirci.note.NoteActivity
import com.demirci.note.NotesApplication
import com.demirci.note.R
import com.demirci.note.common.ImageUtils
import com.demirci.note.databinding.FragmentNoteAddBinding
import com.demirci.note.db.model.Note
import com.demirci.note.db.model.NoteState
import es.dmoral.toasty.Toasty

class NoteAddFragment : Fragment() {

    private lateinit var binding: FragmentNoteAddBinding

    private val viewModel: NoteAddViewModel by viewModels {
        NoteAddViewModelFactory(((requireActivity().application) as NotesApplication).noteRepository)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAddBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
    }

    private fun initEvents() {
        binding.buttonNoteAdd.setOnClickListener {
            if (inputCheck()) {
                val note = Note(
                    0,
                    (requireActivity() as NoteActivity).user.uid,
                    viewModel.title,
                    viewModel.description,
                    viewModel.imagePath,
                    NoteState.TODO,
                    System.currentTimeMillis()
                )
                viewModel.insert(note)
                Toasty.success(requireContext(), getString(R.string.note_added), Toast.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()
            }
        }

        binding.imageView.setOnClickListener {
            ImageUtils.checkPermission(this)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun inputCheck(): Boolean {
        viewModel.title = binding.outlinedTextFieldNoteTitle.editText?.text.toString()
        viewModel.description = binding.outlinedTextFieldNoteDescription.editText?.text.toString()
        binding.outlinedTextFieldNoteTitle.error = null
        binding.outlinedTextFieldNoteDescription.error = null

        var check = true

        if (viewModel.title.isEmpty()) {
            binding.outlinedTextFieldNoteTitle.error = "The field is required"
            check = false
        }

        if (viewModel.description.isEmpty()) {
            binding.outlinedTextFieldNoteDescription.error = "The field is required"
            check = false
        }
        return check
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ImageUtils.PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission verildiyse
                    ImageUtils.pickImageFromGallery(this)
                } else {
                    Toasty.error(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("girdimi", "evet")
        if (resultCode == Activity.RESULT_OK && requestCode == ImageUtils.IMAGE_PICK_CODE) {
            Log.e("imagePick", data?.data.toString())
            viewModel.imagePath = data?.data.toString()
            Glide
                .with(this)
                .load(Uri.parse(data?.data.toString()))
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.image_256)
                .into(binding.imageView);

            //binding.imageView.setImageURI(data?.data)
        }
    }

}