package com.example.shift_1809

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SharedMemory
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.shift_1809.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private var APP_SETTINGS: String = "APP_SETTINGS"
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPref = getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag)
                    as NavHostFragment
        val navController = navHostFragment.navController
        if (sharedPref.getString("name", "") != "") {
            Repository.createUser(
                sharedPref.getString("name", "")!!,
                sharedPref.getString("surname", "")!!,
                sharedPref.getString("password", "")!!,
                Date(sharedPref.getString("date", "")!!)
            )
            navController.navigate(R.id.mainFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        val prefs = sharedPref.edit()
        prefs.putString("name", Repository.getUserName())
        prefs.putString("surname", Repository.getUserSurname())
        prefs.putString("date", Repository.getUserDate())
        prefs.putString("password", Repository.getUserPassword())
        prefs.apply()
    }
}