package com.example.shift_1809.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shift_1809.R
import com.example.shift_1809.Repository
import com.example.shift_1809.databinding.RegistrationFrameBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date
import java.util.regex.Pattern

class RegisterFragment : Fragment() {
    private var _binding: RegistrationFrameBinding? = null
    private val binding get() = _binding!!
    private var name: String = ""
    private var surname: String = ""
    private var date: Date? = null
    private var password: String? = null
    private var confirmPasswordState: Boolean = false
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.doAfterTextChanged {
            if (it!!.length >= 2)
                name = it.toString()
            else binding.name.error = "Имя должно содержать больше двух символов"
            checkFields()
        }
        binding.datePicker.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(parentFragmentManager, "get Date")
            datePicker.addOnPositiveButtonClickListener {
                date = Date(it)
            }
            checkFields()
        }
        binding.surname.doAfterTextChanged {
            if (it!!.length >= 2)
                surname = it.toString()
            else
                binding.surname.error = "Фамилия должна содержать больше двух символов"
            checkFields()
        }
        binding.password.doAfterTextChanged {
            if (validPassword(it.toString()))
                password = it.toString()
            else
                binding.password.error =
                    "Пароль не содержит заглавные буквы, цифры или специальные символы"
            checkFields()
        }
        binding.confirmPassword.doAfterTextChanged {
            confirmPasswordState = password == it.toString()
            if (!confirmPasswordState)
                binding.confirmPassword.error = "Пароли не совпадают"
            checkFields()
        }

        binding.regButton.setOnClickListener {
            Repository.createUser(name, surname, password!!, date!!)

            findNavController().navigate(R.id.mainFragment)
        }
    }

    private fun checkFields() {
        binding.regButton.isEnabled =
            (date != null) and (name != "") and (surname != "") and confirmPasswordState
    }

    private fun validPassword(password: String): Boolean {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFrameBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
}