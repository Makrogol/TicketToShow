package com.example.tickettoshow.application.view.registerscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.tickettoshow.R
import com.example.tickettoshow.application.model.user.DataUser
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.submitemailscreen.SubmitEmailFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.databinding.FragmentRegisterScreenBinding
import com.example.tickettoshow.foundation.tools.checkPassword
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel


class RegisterScreenFragment : BaseFragment() {

    class Screen : BaseScreen

    private lateinit var binding: FragmentRegisterScreenBinding

    override val viewModel by screenViewModel<RegisterScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterScreenBinding.inflate(inflater, container, false)


        binding.nameEdittext.doOnTextChanged { text, start, before, count ->
            binding.nameEdittext.tag = checkNameField()
            doRegisterButtonClickable()
        }

        binding.passwordEdittext.doAfterTextChanged {
            binding.passwordEdittext.tag = checkCorrectPassword()
            doRegisterButtonClickable()
        }

        binding.confirmPasswordEdittext.doAfterTextChanged {
            binding.confirmPasswordEdittext.tag = checkEqualPassword()
            doRegisterButtonClickable()
        }

        binding.emailEdittext.doAfterTextChanged {
            binding.emailEdittext.tag = checkEmail()
            doRegisterButtonClickable()
        }



        binding.registrationButton.setOnClickListener {
            viewModel.registerUser(
                DataUser(
                    id = -1,
                    name = binding.nameEdittext.text.toString(),
                    email = binding.emailEdittext.text.toString(),
                    password = binding.passwordEdittext.text.toString()
                )
            )
        }

        viewModel.userChecked.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    binding.emailEdittext.tag = it
                    doRegisterButtonClickable()
                }
            )
        }

        viewModel.userRegistered.observe(viewLifecycleOwner) {
            renderSimpleResult(
                root = binding.root,
                result = it,
                onSuccess = {
                    viewModel.launch(SubmitEmailFragment.Screen())
                }
            )
        }

        binding.backButton.setOnClickListener {
            //todo
            viewModel.launch(HomeScreenFragment.Screen())
        }

        return binding.root
    }


    private fun checkNameField(): Boolean {
        return drawStateEditText(
            state = binding.nameEdittext.text.toString().isEmpty(),
            errorText = "",
            defaultHint = R.string.your_name_register_screen_hint_text.toString(),
            editText = binding.nameEdittext,
            onGoodState = {}
        )
    }


    private fun checkCorrectPassword(): Boolean {
        return drawStateEditText(
            state = checkPassword(binding.passwordEdittext.text.toString()),
            errorText = R.string.incorrect_password_masks_error_text.toString(),
            defaultHint = binding.passwordEdittext.hint.toString(),
            editText = binding.passwordEdittext,
            onGoodState = {}
        )
    }


    private fun checkEqualPassword(): Boolean {
        val state =
            binding.passwordEdittext.text.toString() == binding.confirmPasswordEdittext.text.toString()
        drawStateEditText(
            state = state,
            errorText = R.string.password_is_not_equal_error_text.toString(),
            defaultHint = binding.passwordEdittext.hint.toString(),
            editText = binding.passwordEdittext,
            onGoodState = {}
        )

        return drawStateEditText(
            state = state,
            errorText = R.string.password_is_not_equal_error_text.toString(),
            defaultHint = binding.confirmPasswordEdittext.hint.toString(),
            editText = binding.confirmPasswordEdittext,
            onGoodState = {}
        )
    }

    private fun checkEmail(): Boolean {
        val editText = binding.emailEdittext
        return drawStateEditText(
            state = editText.text.isEmpty(),
            errorText = "",
            defaultHint = editText.hint.toString(),
            editText = editText,
            onGoodState = { viewModel.checkUserByEmail(editText.text.toString()) }
        )
    }


    private fun doRegisterButtonClickable() {
        binding.registrationButton.isClickable = with(binding) {
            (
                    (nameEdittext.tag as Boolean) and
                            (passwordEdittext.tag as Boolean) and
                            (confirmPasswordEdittext.tag as Boolean) and
                            (emailEdittext.tag as Boolean)
                    )
        }
        if (binding.registrationButton.isClickable) {
            binding.registrationButton.setBackgroundResource(R.drawable.bg_register_button_on)
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun drawStateEditText(
        state: Boolean,
        errorText: String,
        defaultHint: String,
        editText: EditText,
        onGoodState: () -> Unit
    ): Boolean {

        binding.registerScreenErrorTextview.visibility = View.GONE

        if (state) {
            with(editText) {
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ok_register_screen_icon,
                    0
                )
                setHintTextColor(R.color.text_hint_color)
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
                hint = defaultHint
            }
            onGoodState()

        } else {
            if (editText.text.isEmpty()) {

                with(editText) {
                    hint = R.string.field_must_be_fill_text.toString()
                    setHintTextColor(R.color.red)
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
                }

            } else {
                with(editText) {
                    setHintTextColor(R.color.red)
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.bad_register_screen_icon,
                        0
                    )
                }
                with(binding.registerScreenErrorTextview) {
                    visibility = View.VISIBLE
                    text = errorText
                }

            }

        }

        return state
    }
}