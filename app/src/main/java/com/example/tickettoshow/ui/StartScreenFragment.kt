package com.example.tickettoshow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentStartScreenBinding


class StartScreenFragment : Fragment() {

    private lateinit var binding: FragmentStartScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartScreenBinding.inflate(inflater, container, false)

        binding.entryButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToEntryScreen()
            }
        }

        binding.registrationButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToRegisterScreen()
            }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = StartScreenFragment()
    }
}