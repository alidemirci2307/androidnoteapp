package com.demirci.note.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demirci.note.NotesApplication
import com.demirci.note.databinding.FragmentSignUpBinding
import com.demirci.note.db.model.User
import es.dmoral.toasty.Toasty

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(((requireActivity().application) as NotesApplication).userRepository)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate view and obtain an instance of the binding class
        binding = FragmentSignUpBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignIn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonSignUp.setOnClickListener {
            insertUser()
        }

    }

    private fun insertUser(){

        if(inputCheck()){
            viewModel.insert(User(0, viewModel.username, viewModel.password))
            Toasty.success(requireContext(),"User added",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun inputCheck() : Boolean{
        viewModel.username = binding.outlinedTextFieldUsername.editText?.text.toString()
        viewModel.password = binding.outlinedTextFieldPassword.editText?.text.toString()
        binding.outlinedTextFieldUsername.error = null
        binding.outlinedTextFieldPassword.error = null

        var check = true

        if(viewModel.username.isEmpty()){
            binding.outlinedTextFieldUsername.error = "The field is required"
            check = false
        }

        if(viewModel.password.isEmpty()){
            binding.outlinedTextFieldPassword.error = "The field is required"
            check = false
        }
        return check
    }

}