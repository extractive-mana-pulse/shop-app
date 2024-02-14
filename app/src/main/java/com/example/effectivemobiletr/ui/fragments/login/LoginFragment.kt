package com.example.effectivemobiletr.ui.fragments.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletr.R
import com.example.effectivemobiletr.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            signInBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_catalogFragment)
            }
            helloWorld.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_catalogFragment) }

            val nameWatcher = object : TextWatcher {

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s.toString()

                    val cyrillicRegex = Regex("[а-яА-ЯёЁ]+")
                    val specialCharRegex = Regex("[!\"№%:,.;()_+\\-\\s]+")

                    when {
                        !text.matches(cyrillicRegex) -> {
                            loginNameLayout.error = "Можно вводить только кириллицу"
                            binding.signInBtn.isEnabled = false
                        }
                        text.matches(specialCharRegex) -> {
                            loginNameLayout.error = "Нельзя использовать специальные символы и цифры"
                            binding.signInBtn.isEnabled = false
                        }
                        else -> {
                            loginNameLayout.error = null
                            binding.signInBtn.isEnabled = !binding.nameEt.text.isNullOrBlank() &&
                                    !binding.surnameEt.text.isNullOrBlank() &&
                                    !binding.phoneNumberEt.text.isNullOrBlank()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    //
                }
            }

            val surnameWatcher = object : TextWatcher {

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s.toString()

                    val cyrillicRegex = Regex("[а-яА-ЯёЁ]+")
                    val specialCharRegex = Regex("[!\"№%:,.;()_+\\-\\s]+")

                    when {
                        !text.matches(cyrillicRegex) -> {
                            loginSurnameLayout.error = "Можно вводить только кириллицу"
                            binding.signInBtn.isEnabled = false
                        }
                        text.matches(specialCharRegex) -> {
                            loginSurnameLayout.error = "Нельзя использовать специальные символы и цифры"
                            binding.signInBtn.isEnabled = false
                        }
                        else -> {
                            loginSurnameLayout.error = null
                            binding.signInBtn.isEnabled = !binding.nameEt.text.isNullOrBlank() &&
                                    !binding.surnameEt.text.isNullOrBlank() &&
                                    !binding.phoneNumberEt.text.isNullOrBlank()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    //
                }
            }

            val phoneWatcher = object : TextWatcher {

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s.toString()

                    val specialCharRegex = Regex("[!\"№%:,.;()_+\\-\\s]+")

                    when {
                        text.matches(specialCharRegex) -> {
                            loginPnLayout.error = "Нельзя использовать специальные символы и цифры"
                            binding.signInBtn.isEnabled = false
                        }
                        else -> {
                            loginPnLayout.error = null
                            binding.signInBtn.isEnabled = !binding.nameEt.text.isNullOrBlank() &&
                                    !binding.surnameEt.text.isNullOrBlank() &&
                                    !binding.phoneNumberEt.text.isNullOrBlank()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    //
                }
            }

            binding.nameEt.addTextChangedListener(nameWatcher)
            binding.surnameEt.addTextChangedListener(surnameWatcher)
            binding.phoneNumberEt.addTextChangedListener(phoneWatcher)

            val spannableString = SpannableString(underlineTv.text.toString())
            spannableString.setSpan(UnderlineSpan(), 0, underlineTv.text.toString().length, 0)

            underlineTv.text = spannableString
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}