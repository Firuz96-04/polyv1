package com.example.polyv1.MainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polyv1.R
import com.example.polyv1.databinding.FragmentNotificationBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.notifactionMinititle.text = getData()
        return view

    }


    fun getData(): String {
        val simpleData = SimpleDateFormat("dd/MM/yyyy  HH:mm")
        val currdate: String = simpleData.format(Date())
        return currdate
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}