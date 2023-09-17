package com.example.tickettoshow.application

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import com.example.tickettoshow.R
import com.example.tickettoshow.databinding.PartResultBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.model.Result
import com.example.tickettoshow.foundation.views.BaseViewModel
import java.lang.Exception

fun <T> BaseFragment.renderSimpleResult(
    root: ViewGroup,
    result: Result<T>,
    onError: (e: Exception) -> Unit,
    onSuccess: (T) -> Unit
) {
    val binding = PartResultBinding.bind(root)
    renderResult(
        root = root,
        result = result,
        onPending = {
            binding.progressBar.visibility = View.VISIBLE
        },
        onError = {
//            binding.errorContainer.visibility = View.VISIBLE
                  onError(it)
        },
        onSuccess = { successData ->
            root.children
                .filter { it.id != R.id.progress_bar && it.id != R.id.error_container }
                .forEach { it.visibility = View.VISIBLE }
            onSuccess(successData)
        }
    )
}

fun BaseFragment.onTryAgain(root: View, onTryAgainPressed: () -> Unit) {
    root.findViewById<Button>(R.id.try_again_button).setOnClickListener { onTryAgainPressed() }
}