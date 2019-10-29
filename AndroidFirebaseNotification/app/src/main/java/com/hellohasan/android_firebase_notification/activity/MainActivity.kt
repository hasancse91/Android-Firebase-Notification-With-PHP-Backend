package com.hellohasan.android_firebase_notification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.hellohasan.android_firebase_notification.R
import com.hellohasan.android_firebase_notification.notification.Configuration.Companion.TOPIC_GLOBAL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            firebaseId.append(it.token)
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL)
            .addOnCompleteListener { task ->

                if (task.isSuccessful)
                    Log.d(TAG, "Global topic subscription successful")
                else
                    Log.e(TAG, "Global topic subscription failed. Error: " + task.exception?.localizedMessage)
            }
    }
}
