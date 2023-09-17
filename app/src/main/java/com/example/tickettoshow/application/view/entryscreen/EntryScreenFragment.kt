package com.example.tickettoshow.application.view.entryscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.changepasswordscreen.ChangePasswordFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.databinding.FragmentEntryScreenBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel

class EntryScreenFragment : BaseFragment() {

    class Screen : BaseScreen

    private lateinit var binding: FragmentEntryScreenBinding
    override val viewModel by screenViewModel<EntryScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryScreenBinding.inflate(inflater, container, false)

        with(binding) {
            forgotPasswordTextview.setOnClickListener {
                viewModel.launch(ChangePasswordFragment.Screen())
            }
            logInButton.setOnClickListener {
                viewModel.logInUser(emailEdittext.text.toString(), passwordEdittext.text.toString())

                viewModel.user.observe(viewLifecycleOwner) { result ->
                    renderSimpleResult(
                        root = binding.root,
                        result = result,
                        onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                        onSuccess = {
                            if (it.first == null) {
                                entryScreenErrorTextview.visibility = View.VISIBLE

                                if (it.second == "email_is_bad")
                                    entryScreenErrorTextview.text =
                                        getString(R.string.your_account_is_not_exist_error_text)
                                else if (it.second == "password_is_bad")
                                    entryScreenErrorTextview.text =
                                        getString(R.string.incorrect_password_error_text)
                            } else {
                                viewModel.updateCurrentUserToken(it.first!!)
                                viewModel.launch(HomeScreenFragment.Screen())
                            }
                        }
                    )
                }
            }
        }
        return binding.root
    }

}