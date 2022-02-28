package com.amandeep.androidbackstack.activityWith4fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amandeep.androidbackstack.R
import com.amandeep.androidbackstack.databinding.ActivityMainBinding
import com.amandeep.androidbackstack.fragments.Fragment1
import com.amandeep.androidbackstack.fragments.Fragment2
import com.amandeep.androidbackstack.fragments.Fragment3
import com.amandeep.androidbackstack.fragments.Fragment4

class MainActivityWith4Fragments : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ")
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame1, Fragment1.newInstance("", "")).addToBackStack("One").commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame2, Fragment2.newInstance("", "")).addToBackStack("Two").commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.frame3, Fragment3.newInstance("", "")).commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame4, Fragment4.newInstance("", "")).addToBackStack("Four").commit()


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

    override fun onBackPressed() {
        Log.e(TAG, "onBackPressed: ", )
        super.onBackPressed()
    }

}