package com.demirci.note.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demirci.note.NoteActivity
import com.demirci.note.NotesApplication
import com.demirci.note.databinding.FragmentLoginBinding
import es.dmoral.toasty.Toasty

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(((requireActivity().application) as NotesApplication).userRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        binding.buttonSignIn.setOnClickListener {
            if(inputCheck()){
                viewModel.getUser(viewModel.username, viewModel.password)
                viewModel.user.observe(viewLifecycleOwner, {
                    if(it != null){
                        Toasty.success(requireContext(),"Giriş Başarılı : "+it.username,Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireContext(),NoteActivity::class.java)
                        intent.putExtra("user",it)
                        (requireActivity() as Activity).finish()
                        requireActivity().startActivity(intent)
                    }else{
                        Toasty.error(requireContext(),"Giriş Başarısız",Toast.LENGTH_SHORT).show()
                    }
                })
            }
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