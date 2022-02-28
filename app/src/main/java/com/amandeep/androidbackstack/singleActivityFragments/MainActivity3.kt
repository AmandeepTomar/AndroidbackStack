package com.amandeep.androidbackstack.singleActivityFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amandeep.androidbackstack.R
import com.amandeep.androidbackstack.databinding.ActivityMain3Binding
import com.amandeep.androidbackstack.fragments.Fragment2

class MainActivity3 : AppCompatActivity() {
    private val TAG = "MainActivity3"
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e(TAG, "onCreate: ", )

        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.activity3Container,
                Fragment2.newInstance("", ""),
                ""
            )
            addToBackStack("MA3F2")
            commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }


    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        Log.e(TAG, "onResumeFragments: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    // will discuss about this
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}