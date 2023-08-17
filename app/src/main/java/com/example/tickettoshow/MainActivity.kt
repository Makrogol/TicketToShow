package com.example.tickettoshow

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.tickettoshow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActivityNavigator {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        goToStartScreen()
    }

    override fun goToHomeScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeScreenFragment.newInstance())
            .commit()
    }

    override fun goToRegisterScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RegisterScreenFragment.newInstance())
            .commit()
    }

    override fun goToEntryScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EntryScreenFragment.newInstance())
            .commit()
    }

    override fun goToStartScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, StartScreenFragment.newInstance())
            .commit()
    }

    override fun goToSubmitEmailScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SubmitEmailFragment.newInstance())
            .commit()
    }
}