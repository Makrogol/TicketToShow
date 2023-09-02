package com.example.tickettoshow.application.view.submitemailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.tickettoshow.application.view.ActivityNavigator
import com.example.tickettoshow.databinding.FragmentSubmitEmailBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.screenViewModel


class SubmitEmailFragment : BaseFragment() {

    class Screen : BaseScreen


    override val viewModel by screenViewModel<SubmitEmailViewModel>()
    private lateinit var binding: FragmentSubmitEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmitEmailBinding.inflate(inflater, container, false)

        binding.submitEmail1Edittext.doOnTextChanged { text, start, before, count ->

        }

        binding.registrationButton.setOnClickListener {

        }

        binding.backButton.setOnClickListener {

        }

        return binding.root

    }


}