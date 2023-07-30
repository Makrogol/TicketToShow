package com.example.tickettoshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentEntryScreenBinding
import com.example.tickettoshow.databinding.FragmentStartScreenBinding

class EntryScreenFragment : Fragment() {

    private lateinit var binding: FragmentEntryScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryScreenBinding.inflate(inflater, container, false)

        binding.entryButton.setOnClickListener {
            if (activity is ActivityNavigator) {
                (activity as ActivityNavigator).goToHomeScreen()
            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = EntryScreenFragment()
    }
}