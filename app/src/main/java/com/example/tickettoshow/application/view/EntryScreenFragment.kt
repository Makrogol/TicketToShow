package com.example.tickettoshow.application.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentEntryScreenBinding
import com.example.tickettoshow.foundation.views.BaseScreen

class EntryScreenFragment : Fragment() {

    class Screen: BaseScreen

    private lateinit var binding: FragmentEntryScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = EntryScreenFragment()
    }
}