package com.example.tickettoshow.application.view.changepasswordscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.tickettoshow.R
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.entryscreen.EntryScreenFragment
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.databinding.FragmentChangePasswordScreenBinding
import com.example.tickettoshow.foundation.tools.checkPassword
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel

class ChangePasswordFragment : BaseFragment() {
    class Screen : BaseScreen

    private lateinit var binding: FragmentChangePasswordScreenBinding
    override val viewModel by screenViewModel<ChangePasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordScreenBinding.inflate(inflater, container, false)
        val inputGood = mutableListOf(false, false, false)

        with(binding) {
            emailEdittext.doAfterTextChanged {
                inputGood[0] = checkEmail(it?.toString())
            }

            passwordEdittext.doAfterTextChanged {
                inputGood[1] = checkCorrectPassword(it?.toString())
            }

            confirmPasswordEdittext.doAfterTextChanged {
                inputGood[2] = checkEqualPassword(it?.toString())
            }

            backButton.setOnClickListener {
                if (emailEdittext.visibility != View.GONE)
                    viewModel.launch(HomeScreenFragment.Screen())
                if (passwordEdittext.visibility != View.GONE) {
                    passwordEdittext.visibility = View.GONE
                    confirmPasswordEdittext.visibility = View.GONE
                    emailEdittext.visibility = View.VISIBLE
                }
            }


            nextButton.setOnClickListener {
                if (emailEdittext.visibility != View.GONE) {
                    if (inputGood[0]) {
                        viewModel.setEmail(emailEdittext.text.toString())
                        viewModel.checkEmail()

                        viewModel.emailChecked.observe(viewLifecycleOwner) { result ->
                            renderSimpleResult(
                                root = root,
                                result = result,
                                onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                                onSuccess = { check_email ->
                                    if (check_email) {
                                        passwordEdittext.visibility = View.VISIBLE
                                        confirmPasswordEdittext.visibility = View.VISIBLE
                                        emailEdittext.visibility = View.GONE
                                    } else {
                                        drawStateEditText(
                                            state = false,
                                            errorText = getString(R.string.that_email_is_not_exist_text),
                                            defaultHint = emailEdittext.hint.toString(),
                                            editText = emailEdittext,
                                            inputText = emailEdittext.text.toString(),
                                            onGoodState = {}
                                        )
                                    }
                                }
                            )
                        }


                    }
                }
                if (passwordEdittext.visibility != View.GONE) {
                    if (inputGood[1] && inputGood[2]) {
                        viewModel.setPassword(passwordEdittext.text.toString())
                        viewModel.changeUserPassword()

                        viewModel.is_password_changed.observe(viewLifecycleOwner) { result ->
                            renderSimpleResult(
                                root = root,
                                result = result,
                                onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                                onSuccess = {
                                    passwordEdittext.visibility = View.GONE
                                    confirmPasswordEdittext.visibility = View.GONE
                                    resetPasswordTextview.visibility = View.GONE
                                    passwordIsChangedImg.visibility = View.VISIBLE
                                    passwordIsChangedTextview.visibility = View.VISIBLE
                                    nextButton.visibility = View.GONE
                                    backButton.visibility = View.GONE
                                    logInButton.visibility = View.VISIBLE
                                }
                            )
                        }
                    }
                }
            }



            logInButton.setOnClickListener {
                viewModel.launch(EntryScreenFragment.Screen())
            }
        }

        return binding.root
    }


    private fun checkEmail(text: String?): Boolean {
        val editText = binding.emailEdittext
        return drawStateEditText(
            state = !text.isNullOrEmpty(),
            errorText = "",
            defaultHint = editText.hint.toString(),
            editText = editText,
            inputText = text,
            onGoodState = { viewModel.setEmail(text!!) }
        )
    }

    private fun checkCorrectPassword(text: String?): Boolean {
        return drawStateEditText(
            state = !text.isNullOrEmpty() and checkPassword(text.toString()),
            errorText = getString(R.string.incorrect_password_masks_error_text),
            defaultHint = binding.passwordEdittext.hint.toString(),
            editText = binding.passwordEdittext,
            inputText = text,
            onGoodState = {}
        )
    }


    private fun checkEqualPassword(text: String?): Boolean {
        if (text.isNullOrEmpty()) {
            drawStateEditText(
                state = true,
                errorText = getString(R.string.password_is_not_equal_error_text),
                defaultHint = binding.passwordEdittext.hint.toString(),
                editText = binding.passwordEdittext,
                onGoodState = {}
            )
            return drawStateEditText(
                state = !text.isNullOrEmpty(),
                errorText = getString(R.string.password_is_not_equal_error_text),
                defaultHint = binding.confirmPasswordEdittext.hint.toString(),
                editText = binding.confirmPasswordEdittext,
                inputText = text,
                onGoodState = {}
            )
        }

        val state = binding.passwordEdittext.text.toString() == text
        drawStateEditText(
            state = state,
            errorText = getString(R.string.password_is_not_equal_error_text),
            defaultHint = binding.passwordEdittext.hint.toString(),
            editText = binding.passwordEdittext,
            inputText = binding.passwordEdittext.text.toString(),
            onGoodState = {}
        )

        return drawStateEditText(
            state = state,
            errorText = getString(R.string.password_is_not_equal_error_text),
            defaultHint = binding.confirmPasswordEdittext.hint.toString(),
            editText = binding.confirmPasswordEdittext,
            inputText = text,
            onGoodState = { viewModel.setPassword(text) }
        )
    }


    @SuppressLint("ResourceAsColor")
    private fun drawStateEditText(
        state: Boolean,
        errorText: String,
        defaultHint: String,
        editText: EditText,
        inputText: String? = null,
        onGoodState: () -> Unit
    ): Boolean {

        binding.forgotPasswordScreenErrorTextview.visibility = View.GONE

        if (state) {
            with(editText) {
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ok_register_screen_icon,
                    0
                )
                setHintTextColor(ContextCompat.getColorStateList(context, R.color.text_hint_color))
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
                hint = defaultHint
            }
            onGoodState()

        } else {
            if (inputText.isNullOrEmpty()) {

                with(editText) {
                    hint = getString(R.string.field_must_be_fill_text)
                    setHintTextColor(ContextCompat.getColorStateList(context, R.color.red))
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.bad_register_screen_icon,
                        0
                    )
                }
            } else {
                with(editText) {
                    setHintTextColor(ContextCompat.getColorStateList(context, R.color.red))
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.bad_register_screen_icon,
                        0
                    )
                }
                if (errorText.isNotEmpty()) {
                    with(binding.forgotPasswordScreenErrorTextview) {
                        visibility = View.VISIBLE
                        text = errorText
                    }
                }

            }
        }

        return state
    }
}