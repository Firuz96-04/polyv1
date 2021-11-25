package com.example.polyv1.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polyv1.R
import com.example.polyv1.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        //  return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}