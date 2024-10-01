package com.ndridm.sportcars

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ndridm.sportcars.databinding.ActivityModeBinding

class ModeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModeBinding
    private lateinit var sharedPreferences:SharedPreferences
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Mode Settings"
        enableEdgeToEdge()
        binding = ActivityModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
        isChecked = sharedPreferences.getBoolean("night", false)

        binding.switchMode.setOnClickListener {
            isChecked = binding.switchMode.isChecked
            val editor = sharedPreferences.edit()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
            }
            editor.apply()
        }
    }
}