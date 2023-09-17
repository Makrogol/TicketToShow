package com.example.tickettoshow.application.view.submitemailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentSubmitEmailBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel


class SubmitEmailScreenFragment : BaseFragment() {

    class Screen : BaseScreen


    override val viewModel by screenViewModel<SubmitEmailScreenViewModel>()
    private lateinit var binding: FragmentSubmitEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmitEmailBinding.inflate(inflater, container, false)

        return binding.root

    }


}