package com.example.shift_1809.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shift_1809.Repository
import com.example.shift_1809.databinding.MainFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.helloButton.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
                com.google.android.material.R.style.MaterialAlertDialog_Material3
            )
                .setTitle("Ваше имя")
                .setMessage(Repository.getUserName())
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
}