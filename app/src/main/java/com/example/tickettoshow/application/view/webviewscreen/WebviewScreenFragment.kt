package com.example.tickettoshow.application.view.webviewscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.tickettoshow.R
import com.example.tickettoshow.application.model.event.DataDateAndPay
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.calendarscreen.CalendarScreenFragment
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.databinding.FragmentWebviewScreenBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class WebviewScreenFragment : BaseFragment(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen(
        val url: String
    ) : BaseScreen

    private lateinit var binding: FragmentWebviewScreenBinding
    override val viewModel by screenViewModel<WebviewScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_home_screen)
            .setIcon(R.drawable.affiche_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        viewModel.url.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                onSuccess = { url ->
                    with(binding.webview) {
                        webViewClient = MyWebViewClient()
                        settings.javaScriptEnabled = true
                        settings.loadWithOverviewMode = true
                        loadUrl(url)
                    }
                }
            )
        }

        return binding.root
    }
    

    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        else -> false
    }

}