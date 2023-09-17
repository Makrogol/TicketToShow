package com.example.tickettoshow.application

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.tickettoshow.R
import com.example.tickettoshow.foundation.ActivityScopeViewModel
import com.example.tickettoshow.databinding.ActivityMainBinding
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.registerscreen.RegisterScreenFragment
import com.example.tickettoshow.foundation.navigator.FragmentNavigator
import com.example.tickettoshow.foundation.navigator.IntermediateNavigator
import com.example.tickettoshow.foundation.tools.viewModelCreator
import com.example.tickettoshow.foundation.uiactions.AndroidUiActions
import com.example.tickettoshow.foundation.views.FragmentsHolder

class MainActivity : AppCompatActivity(), FragmentsHolder {

    private lateinit var navigator: FragmentNavigator
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            uiActions = AndroidUiActions(applicationContext),
            navigator = IntermediateNavigator()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        navigator = FragmentNavigator(
            activity = this,
            containerId = R.id.container,
            initialScreenCreator = { HomeScreenFragment.Screen() }
        )

        navigator.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        activityViewModel.navigator.setTarget(null)
    }

    override fun notifyScreenUpdates() {
        navigator.notifyScreenUpdates()
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel {
        return activityViewModel
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
        super.onBackPressed()
    }


}