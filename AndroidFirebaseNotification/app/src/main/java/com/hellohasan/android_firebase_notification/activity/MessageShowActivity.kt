package com.hellohasan.android_firebase_notification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.hellohasan.android_firebase_notification.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_message_show.*


class MessageShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_show)

        //receive data from MyFirebaseMessagingService class
        val title = intent.getStringExtra("title")
        val timeStampString = intent.getStringExtra("timestamp")
        val articleString = intent.getStringExtra("article_data")
        val imageUrl = intent.getStringExtra("image")

        //Set data on UI
        header.text = title
        timeStamp.text = timeStampString
        article.text = articleString

        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.default_image)
            .into(featureGraphics)
    }
}
