package com.example.palace_resorts.flows.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.palace_resorts.base.FragmentView
import com.example.palace_resorts.databinding.FragmentSigninBinding
import com.example.palace_resorts.utils.email.EmailUtils

class SignInFragment : FragmentView() {

    private val binding by lazy {
        FragmentSigninBinding.inflate(layoutInflater)
    }

    private var listener : SignInFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SignInFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonSignIn.setOnClickListener {
                val isEmailValid = EmailUtils.validateEmail(editTextEmail.text.toString())
                if (isEmailValid) {
                    inputLayoutEmail.error = ""
                } else {
                    inputLayoutEmail.error = "Correo no válido, intenta de nuevo"
                }

                val isPasswordValid = EmailUtils.validatePassword(editTextPassword.text.toString())
                if (isPasswordValid) {
                    inputLayoutPassword.error = ""
                } else {
                    inputLayoutPassword.error = "Contraseña no válida, intenta de nuevo"
                }

                if (isEmailValid && isPasswordValid) {
                    listener?.navigateToHome()
                }
            }
        }
    }

    interface SignInFragmentListener {
        fun navigateToHome()
    }
}