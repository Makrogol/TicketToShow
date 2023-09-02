package com.example.tickettoshow.foundation.views

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.tickettoshow.foundation.model.ErrorResult
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.model.Result
import com.example.tickettoshow.foundation.model.SuccessResult
import java.lang.Exception

//Базовый класс для фрагмента

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

    fun <T> renderResult(
        root: ViewGroup, result: Result<T>,
        onPending: () -> Unit,
        onError: (Exception) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        root.children.forEach { it.visibility = View.GONE }

        when (result) {
            is SuccessResult -> onSuccess(result.data)
            is PendingResult -> onPending()
            is ErrorResult -> onError(result.exception)
        }
    }

    fun notifyScreenUpdates() {
        (requireActivity() as FragmentsHolder).notifyScreenUpdates()
    }

}