package com.example.tickettoshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentRegisterScreenBinding


class RegisterScreenFragment : Fragment() {

    private lateinit var binding: FragmentRegisterScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterScreenBinding.inflate(inflater, container, false)

        binding.registrationButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToSubmitEmailScreen()
            }
        }

        binding.backButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToStartScreen()
            }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = RegisterScreenFragment()
    }
}