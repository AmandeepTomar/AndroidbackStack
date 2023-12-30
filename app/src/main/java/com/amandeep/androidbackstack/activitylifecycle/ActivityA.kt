package com.amandeep.androidbackstack.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.amandeep.androidbackstack.MainActivity2
import com.amandeep.androidbackstack.singleActivityFragments.MainActivity3
import com.amandeep.androidbackstack.activityWith4fragments.MainActivityWith4Fragments
import com.amandeep.androidbackstack.databinding.ActivityActivityBinding

class ActivityA : AppCompatActivity(),
    LoggingState by LoggingStateImpl(),
    Deeplink by DeeplinkImpl() {
    private val TAG = "ActivityA"
    private lateinit var binding: ActivityActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e(TAG, "onCreate: ")

        registerLifeCycleOwner(this)
        handleDeeplink(this, intent)

        binding.btnActivityLifeCycle.setOnClickListener {
            // in this we check lifeCycle Activity for ActivityA and ActivityB
            val data = ShareDataClassWithParcelable("Yes We get this", true, true)
            val data1 = ShareNormalClass("AndroidShareData", true)
            val intent = Intent(this, ActivityB::class.java)
            val bundle = Bundle()
            bundle.putParcelable("Bundle", data1)

            intent.putExtra("Data", bundle);
            startActivity(intent)
        }

        binding.btnSingleActivityWith4Frag.setOnClickListener {
            //  UseCase A Activity Having Four Fragments
            //  one without add to back stack. with add
            // All have add to back stack
            startActivity(Intent(this, MainActivityWith4Fragments::class.java))
        }

        binding.FragmentLifeCycle.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))

        }

        binding.FragmentLifeCycleWithActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
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

//    override fun onResume() {
//        super.onResume()
//        Log.e(TAG, "onResume: ")
//    }
//
//
//    override fun onPause() {
//        super.onPause()
//        Log.e(TAG, "onPause: ")
//    }

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

// understand the deligates we create a interface that log the onpause and on resume

public interface LoggingState {
    fun registerLifeCycleOwner(owner: LifecycleOwner)
}

class LoggingStateImpl : LoggingState, LifecycleEventObserver {
    override fun registerLifeCycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> Log.e("TAG", "onStateChanged: onResumed Called")
            Lifecycle.Event.ON_PAUSE -> Log.e("TAG", "onStateChanged: onPause Called")
            else -> Unit
        }
    }

}

// Deligate for DeepLink

interface Deeplink {
    fun handleDeeplink(activityA: ComponentActivity, intent: Intent)
}

class DeeplinkImpl : Deeplink {
    override fun handleDeeplink(activityA: ComponentActivity, intent: Intent) {
        Log.e("TAG", "handleDeeplink: perfomr deeplink logic $activityA $intent")
    }

}