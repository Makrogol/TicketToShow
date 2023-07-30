package com.example.tickettoshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val adapter = ConcertShowAdapter()
        binding.concertsRecyclerview.adapter = adapter
//        adapter.addShows(listOf(
//            DataShow(
//
//            )
//        ))
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeScreenFragment()
    }
}