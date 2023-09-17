package com.example.tickettoshow.application.view.registerscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.databinding.FragmentRegisterScreenBinding
import com.example.tickettoshow.foundation.BaseApplication
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
        val inputGood = mutableListOf(true, false, false, false)
        binding = FragmentRegisterScreenBinding.inflate(inflater, container, false)


//        binding.nameEdittext.doAfterTextChanged {
//            inputGood[0] = checkNameField(it?.toString())
//        }

        binding.passwordEdittext.doAfterTextChanged {
            inputGood[1] = checkCorrectPassword(it?.toString())
        }

        binding.confirmPasswordEdittext.doAfterTextChanged {
            inputGood[2] = checkEqualPassword(it?.toString())
        }

        binding.emailEdittext.doAfterTextChanged {
            inputGood[3] = checkEmail(it?.toString())

            viewModel.userChecked.observe(viewLifecycleOwner) { result ->
                renderSimpleResult(
                    root = binding.root,
                    result = result,
                    onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                    onSuccess = { check_email ->
                        inputGood[3] = check_email
                        if (!check_email) {
                            setUserWithEmailIsExist(it?.toString())
                        }
                    }
                )
            }
        }



        binding.registrationButton.setOnClickListener {
            if (inputGood.all { it }) {
                viewModel.registerUser(
                    DataUser(
                        id = -1,
//                        name = binding.nameEdittext.text.toString(),
                        name = "",
                        email = binding.emailEdittext.text.toString(),
                        password = binding.passwordEdittext.text.toString()
                    )
                )
                viewModel.userRegistered.observe(viewLifecycleOwner) {
                    renderSimpleResult(
                        root = binding.root,
                        result = it,
                        onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                        onSuccess = { user ->
                            (requireActivity().application as App).currentUser = user
                            viewModel.launch(HomeScreenFragment.Screen())
                        }
                    )
                }
            } else
                viewModel.toast(getString(R.string.not_all_input_is_good))
        }




        binding.backButton.setOnClickListener {
            //todo
            viewModel.launch(HomeScreenFragment.Screen())
        }

        return binding.root
    }


//    private fun checkNameField(text: String?): Boolean {
//        return drawStateEditText(
//            state = !text.isNullOrEmpty(),
//            errorText = "",
//            defaultHint = getString(R.string.your_name_register_screen_hint_text),
//            editText = binding.nameEdittext,
//            inputText = text,
//            onGoodState = {}
//        )
//    }


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
            onGoodState = {}
        )
    }

    //pas = 12gHg#hj

    private fun setUserWithEmailIsExist(text: String?) {
        drawStateEditText(
            state = false,
            errorText = getString(R.string.user_with_this_email_is_already_exist),
            defaultHint = binding.emailEdittext.hint.toString(),
            editText = binding.emailEdittext,
            inputText = text,
            onGoodState = {}
        )
    }

    private fun checkEmail(text: String?): Boolean {
        val editText = binding.emailEdittext
        return drawStateEditText(
            state = !text.isNullOrEmpty(),
            errorText = "",
            defaultHint = editText.hint.toString(),
            editText = editText,
            inputText = text,
            onGoodState = { viewModel.checkUserByEmail(editText.text.toString()) }
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

        binding.registerScreenErrorTextview.visibility = View.GONE

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
                    with(binding.registerScreenErrorTextview) {
                        visibility = View.VISIBLE
                        text = errorText
                    }
                }

            }

        }

        return state
    }
}