package com.example.tickettoshow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentSubmitEmailBinding


class SubmitEmailFragment : Fragment() {

    private lateinit var binding: FragmentSubmitEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmitEmailBinding.inflate(inflater, container, false)

        binding.registrationButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToHomeScreen()
            }
        }

        binding.backButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToRegisterScreen()
            }
        }

        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = SubmitEmailFragment()
    }
}